Page({
  data:{

  },
  onLoad(options){
    console.log(options.seller_id)
  },
  onShow() {
    let app = getApp();
    if(app.data.is_login) {
      wx.request({
        url: 'http://127.0.0.1/',
        method:"post",
        success(resp) {

        }
      })
    }
  },
})