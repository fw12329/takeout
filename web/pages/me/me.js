Page({
  data:{
    username:'',
    is_login:false,
    number:''
  },
  onShow(){
    let app = getApp();
    let that = this;
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
          app.data.number = resp.data.number;
          let length = app.data.number.length;
          let number = app.data.number.substring(0,4) + "***" + app.data.number.substring(length - 4,length)
          that.setData({
            username:app.data.username,
            is_login:app.data.is_login,
            number:number
          })
        } else {
          app.data.is_login = false;
          app.data.username = '';
          app.data.number = '';
          that.setData({
            username:app.data.username,
            is_login:app.data.is_login,
            number:app.data.number
          })
        }
      }
    })
  },
  onLoad(){
    let app = getApp();
    let that = this;
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
          app.data.number = resp.data.number;
          let length = app.data.number.length;
          let number = app.data.number.substring(0,4) + "***" + app.data.number.substring(length - 4,length)
          that.setData({
            username:app.data.username,
            is_login:app.data.is_login,
            number:number
          })
        } else {
          app.data.is_login = false;
          app.data.username = '';
          app.data.number = '';
          that.setData({
            username:app.data.username,
            is_login:app.data.is_login,
            number:app.data.number
          })
        }
      }
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
    app.data.number = '';
    this.onLoad();
  }
})