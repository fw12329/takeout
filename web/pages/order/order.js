Page({
  data:{
    order_list:[],
    is_login:false
  },
  onShow(){
    let that = this;
    let app = getApp();
    if(app.data.is_login) {
      wx.request({
        url: 'http://127.0.0.1:8081/user/order/getlist/',
        header:{
          Authorization: "Bearer " + wx.getStorageSync('token')
        },
        success(resp) {
          that.setData({
            order_list:resp.data,
            is_login:app.data.is_login
          })
          console.log(resp)
        }
      })
    } else {
      that.setData({
        is_login:app.data.is_login
      })
    }
  },
  onLoad() {
    let that = this;
    let app = getApp();
    if(app.data.is_login) {
      wx.request({
        url: 'http://127.0.0.1:8081/user/order/getlist/',
        header:{
          Authorization: "Bearer " + wx.getStorageSync('token')
        },
        success(resp) {
          that.setData({
            order_list:resp.data,
            is_login:app.data.is_login
          })
          console.log(resp)
        }
      })
    }
  },
  login() {
    wx.redirectTo({
      url: '../me/login/login',
    })
  }
})