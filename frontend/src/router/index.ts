import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AssessmentView from '../views/AssessmentView.vue';
import CounselingView from '../views/CounselingView.vue';
import CommunityView from '../views/CommunityView.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: HomeView },
    { path: '/assessment', component: AssessmentView },
    { path: '/counseling', component: CounselingView },
    { path: '/community', component: CommunityView },
  ],
});

export default router;
