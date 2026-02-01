const output = document.getElementById('output');

async function fetchJson(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`请求失败: ${response.status}`);
    }
    return response.json();
}

function render(data) {
    output.textContent = JSON.stringify(data, null, 2);
}

async function loadCustomers() {
    try {
        render(await fetchJson('/api/customers'));
    } catch (error) {
        output.textContent = error.message;
    }
}

async function loadTables() {
    try {
        render(await fetchJson('/api/tables'));
    } catch (error) {
        output.textContent = error.message;
    }
}

async function loadReservations() {
    try {
        render(await fetchJson('/api/reservations'));
    } catch (error) {
        output.textContent = error.message;
    }
}

async function loadSummary() {
    try {
        render(await fetchJson('/api/reports/summary'));
    } catch (error) {
        output.textContent = error.message;
    }
}
