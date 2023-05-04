Page({
  onGetUserInfo: function (e) {
    wx.getUserInfo({
      withCredentials: true,
      success: res => {
        console.log(res);
        // res 中包含用户信息和手机号
      }
    })
  }
  
})