import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './action'
Vue.use(Vuex);


const state = { 
    //添加到购物车的商品
    added:[]
}

const getters = {

    //计算总价
    totalPrice:(state,getters)=>{
        let total = 0;
        getters.cartProducts.forEach( item => {
            total += item.price * item.num
        })
        return total.toFixed(2);
    },

    //计算总数量
    totalNum : (state,getters)=>{
        let total = 0;
        getters.cartProducts.forEach( item => {
            total += item.num
        })
        return total;
    },
}

export default new Vuex.Store({
	state,
    getters,
	actions,
	mutations
})





