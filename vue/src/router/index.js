import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

// Fixed the problem that the vue-router in the navigation bar or the bottom navigation tabBar reported an error when clicking the menu frequently in version 3.0 or later.
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/front/home',
    children: [
      { path: '403', name: 'NoAuth', meta: { name: 'No Permission' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: 'System Home' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: 'Admin Information' }, component: () => import('../views/manager/Admin') },
      { path: 'business', name: 'Business', meta: { name: 'Business Information' }, component: () => import('../views/manager/Business') },
      { path: 'user', name: 'User', meta: { name: 'User Information' }, component: () => import('../views/manager/User') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: 'Personal Information' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'businessPerson', name: 'BusinessPerson', meta: { name: 'Personal Information' }, component: () => import('../views/manager/BusinessPerson') },
      { path: 'password', name: 'Password', meta: { name: 'Change Password' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: 'Notice Information' }, component: () => import('../views/manager/Notice') },
      { path: 'type', name: 'Type', meta: { name: 'Type Information' }, component: () => import('../views/manager/Type') },
      { path: 'goods', name: 'Goods', meta: { name: 'Goods Information' }, component: () => import('../views/manager/Goods') },
      { path: 'orders', name: 'Orders', meta: { name: 'Order Management' }, component: () => import('../views/manager/Orders') },
      { path: 'comment', name: 'Comment', meta: { name: 'Comment Management' }, component: () => import('../views/manager/Comment') },

    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'Home', meta: { name: 'System Home' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: 'Personal Information' }, component: () => import('../views/front/Person') },
      { path: 'detail', name: 'Detail', meta: { name: 'Product Details' }, component: () => import('../views/front/Detail') },
      { path: 'type', name: 'Type', meta: { name: 'Category Products' }, component: () => import('../views/front/Type') },
      { path: 'business', name: 'Business', meta: { name: 'Store' }, component: () => import('../views/front/Business') },
      { path: 'collect', name: 'Collect', meta: { name: 'My Favorites' }, component: () => import('../views/front/Collect') },
      { path: 'address', name: 'Address', meta: { name: 'My Address' }, component: () => import('../views/front/Address') },
      { path: 'cart', name: 'Cart', meta: { name: 'My Shopping Cart' }, component: () => import('../views/front/Cart') },
      { path: 'orders', name: 'Orders', meta: { name: 'My Orders' }, component: () => import('../views/front/Orders') },
      { path: 'search', name: 'Search', meta: { name: 'Search Page' }, component: () => import('../views/front/Search') },

    ]
  },
  { path: '/login', name: 'Login', meta: { name: 'Login' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: 'Register' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: 'NotFound' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// Note: Items that do not need a foreground can be commented out
// Route Guard
// router.beforeEach((to ,from, next) => {
//   let user = JSON.parse(localStorage.getItem("xm-user") || '{}');
//   if (to.path === '/') {
//     if (user.role) {
//       if (user.role === 'USER') {
//         next('/front/home')
//       } else {
//         next('/home')
//       }
//     } else {
//       next('/login')
//     }
//   } else {
//     next()
//   }
// })

export default router
