import Login from './subpages/Login/'

import DisplayPlans from './subpages/DisplayPlans/'
import CreatePlan from './subpages/CreatePlan/'

export const routes = [
    {
        path: '/Login',
        component: Login
    },
    {
        path: '/',
        component: DisplayPlans
    },
    {
        path: '/CreatePlan',
        component: CreatePlan
    }
]