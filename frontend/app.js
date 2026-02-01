const API_BASE = "http://localhost:8000/api";

const summaryElements = {
  totalRevenue: document.getElementById("totalRevenue"),
  totalReservations: document.getElementById("totalReservations"),
  activeTables: document.getElementById("activeTables"),
  activeMembers: document.getElementById("activeMembers"),
  busyHours: document.getElementById("busyHours"),
};

const businessHours = document.getElementById("businessHours");
const tableList = document.getElementById("tableList");

const openModalButtons = document.querySelectorAll("[data-modal]");
openModalButtons.forEach((button) => {
  button.addEventListener("click", () => {
    const modal = document.getElementById(button.dataset.modal);
    modal?.showModal();
  });
});

async function fetchJson(path, options = {}) {
  const response = await fetch(`${API_BASE}${path}`, {
    headers: { "Content-Type": "application/json" },
    ...options,
  });

  if (!response.ok) {
    const message = await response.text();
    throw new Error(message || "请求失败");
  }
  return response.json();
}

async function loadSummary() {
  const data = await fetchJson("/reports/summary");
  summaryElements.totalRevenue.textContent = `${data.total_revenue.toFixed(2)} 元`;
  summaryElements.totalReservations.textContent = data.total_reservations;
  summaryElements.activeTables.textContent = data.active_tables;
  summaryElements.activeMembers.textContent = data.active_members;
  summaryElements.busyHours.textContent = data.busy_hours.length
    ? data.busy_hours.map((hour) => `${hour}:00`).join("、")
    : "暂无";
}

async function loadBusinessHours() {
  const data = await fetchJson("/business-hours");
  businessHours.textContent = `${data.open} - ${data.close}`;
}

async function loadTables() {
  const tables = await fetchJson("/tables");
  if (!tables.length) {
    tableList.textContent = "暂无桌位数据";
    return;
  }
  tableList.innerHTML = "";
  tables.forEach((table) => {
    const card = document.createElement("div");
    card.className = "table-item";
    card.innerHTML = `
      <div>
        <strong>${table.label}</strong>
        <div>类型：${table.table_type} / ${table.size}</div>
      </div>
      <div class="table-status">${table.status}</div>
    `;
    tableList.appendChild(card);
  });
}

const formHandlers = {
  userForm: (payload) => fetchJson("/users", { method: "POST", body: JSON.stringify(payload) }),
  tableForm: (payload) => fetchJson("/tables", { method: "POST", body: JSON.stringify(payload) }),
  reservationForm: (payload) =>
    fetchJson("/reservations", { method: "POST", body: JSON.stringify(payload) }),
  billForm: (payload) => fetchJson("/bills", { method: "POST", body: JSON.stringify(payload) }),
};

Object.entries(formHandlers).forEach(([formId, handler]) => {
  const form = document.getElementById(formId);
  form?.addEventListener("submit", async (event) => {
    event.preventDefault();
    const data = Object.fromEntries(new FormData(form).entries());
    ["user_id", "table_id", "reservation_id"].forEach((key) => {
      if (data[key]) {
        data[key] = Number(data[key]);
      }
    });
    ["hourly_rate", "total_amount", "discount_amount", "final_amount"].forEach((key) => {
      if (data[key]) {
        data[key] = Number(data[key]);
      }
    });
    try {
      await handler(data);
      form.closest("dialog")?.close();
      form.reset();
      await Promise.all([loadSummary(), loadTables()]);
    } catch (error) {
      alert(error.message);
    }
  });
});

const refreshSummary = document.getElementById("refreshSummary");
const loadTablesButton = document.getElementById("loadTables");

refreshSummary.addEventListener("click", loadSummary);
loadTablesButton.addEventListener("click", loadTables);

Promise.all([loadSummary(), loadBusinessHours(), loadTables()]).catch((error) => {
  tableList.textContent = "无法连接后端服务，请先启动 API。";
  console.error(error);
});
