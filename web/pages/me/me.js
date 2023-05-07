Page({
  data:{
    username:'',
    is_login:false
  },
  onLoad(){
    let app = getApp();
    wx.request({
      url: 'http://127.0.0.1:8081/user/account/info/',
      method:"get",
      header:{
        Authorization: "Bearer " + wx.getStorageSync('token')
      },
      success(resp) {
        if(resp.data.error_message == "success") {
          app.data.is_login = true;
          app.data.username = resp.data.username;
        }
      }
    })
    this.setData({
      username:app.data.username,
      is_login:app.data.is_login
    })
  },
  login(){
    wx.redirectTo({
      url: './login/login',
    })
  },
  logout() {
    wx.removeStorageSync('token');
    let app = getApp();
    app.data.is_login = false;
    app.data.username = '';
  }
})