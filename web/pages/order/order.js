Page({
  data:{
    order_list:[]
  },
  onLoad() {
    let that = this;
    JSON.stringify
    wx.request({
      url: 'http://127.0.0.1:8081/user/order/getlist/',
      header:{
        Authorization: "Bearer " + wx.getStorageSync('token')
      },
      success(resp) {
        that.setData({
          order_list:resp.data
        })
        console.log(resp)
      }
    })
  }
})