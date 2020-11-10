import Vue from 'vue'
import Router from 'vue-router'

import login from '@/view/login/login'
import enroll from '@/view/login/enroll'

import main from '@/view/main/main'
import home from '@/view/main/home' 
import desk from '@/view/main/desk/desk'

import dish from '@/view/main/dish/dish'
import dishAdd from '@/view/main/dish/dishAdd'
import dishEdit from '@/view/main/dish/dishAdd'

import bill from '@/view/main/bill/bill'

import staff from '@/view/main/staff/staff'
import staffAdd from '@/view/main/staff/staffAdd'
import staffEdit from '@/view/main/staff/staffEdit'

import salary from '@/view/main/salary/salary'
import salaryAdd from '@/view/main/salary/salaryAdd'
import salaryEdit from '@/view/main/salary/salaryEdit'

import  personalOption from '@/view/main/user/personalOption'


Vue.use(Router)

export default new Router({
  routes: [
    {
    path: '/',
    redirect:'login'
    },
    {
      path: '/login',
      name: 'login',
      component: login,
      meta: {
        title: '登录',
      }
    },{
      path: '/enroll',
      name: 'enroll',
      component: enroll,
      meta: {
        title: '注册',
      }
    },
    
    {
      path: '/main',
      name: 'main',
      component: main,
      children: [
        {
          path: '/home',
          name: 'home',
          component: home
        },
        {
          path: '/desk',
          name: 'desk',
          component: desk
        },{
          path: '/dish',
          name: 'dish',
          component: dish
        },{
          path: '/dish/dishAdd',
          name: 'dishAdd',
          props: true,
          component: dishAdd
        },{
          path: '/dish/dishEdit',
          name: 'dishEdit',
          props: true,
          component: dishEdit
        },{
          path: '/bill',
          name: 'bill',
          component: bill
        },{
          path: '/staff',
          name: 'staff',
          props: true,
          component: staff
        },{
          path: '/staff/staffAdd',
          name: 'staffAdd',
          props: true,
          component: staffAdd
        },{
          path: '/staff/staffEdit',
          name: 'staffEdit',
          props: true,
          component: staffEdit
        },{
          path: '/salary',
          name: 'salary',
          component: salary
        },{
          path: '/salary/salaryAdd',
          name: 'salaryAdd',
          props: true,
          component: salaryAdd
        },{
          path: '/salary/salaryEdit',
          name: 'salaryEdit',
          props: true,
          component: salaryEdit
        }, {
          path: '/personalOption',
          name: 'personalOption',
          component: personalOption
        },  
      
      ],
      meta: {
        title: '首页',
        requiresAuth: true // 是否需要判断是否登录
      },
      
    }
  ]
})
