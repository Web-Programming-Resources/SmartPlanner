import Login from './subpages/Login/'
import MyPlans from './subpages/MyPlans/'

export const routes = [
    {
        path: '/Login',
        component: Login
    },
    {
        path: '/',
        component: MyPlans
    }
]