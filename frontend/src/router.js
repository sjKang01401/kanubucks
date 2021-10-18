
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

import OrderManager from "./components/OrderManager"
import UserManager from "./components/UserManager"
import MenuManager from "./components/MenuManager"
import StatusManager from "./components/StatusManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/users',
                name: 'UserManager',
                component: UserManager
            },
            {
                path: '/menus',
                name: 'MenuManager',
                component: MenuManager
            },
            {
                path: '/status',
                name: 'StatusManager',
                component: StatusManager
            },
    ]
})
