Page({
  data: {
    is_login: false
  },
  login() {
    wx.request({
      url: 'https://lxtx.xyz/user/seller/getsellerlist/',
      method:"post",
      data:{
        page:1
      },
      success(resp) {
        console.log(resp)
      }
    })
    let that = this
    that.setData({
      is_login: !that.data.is_login
    })

    wx.login({
      
      success: (res) => {
        wx.request({
          url: 'https://lxtx.xyz/user/account/token/',
          method: 'post',
          data: {
            appid: 'wx5b4cb4e9e492572f',
            secret: 'aa96bb1e00a1f7ee4446dd928e213e52',
            code: res.code
          },
          success(resp) {
            if (resp.data.error_message == "success") {
              wx.setStorageSync('token', resp.data.token)
              let app = getApp();
              app.data.is_login = true;
              app.data.username = resp.data.username
            }
            setTimeout(function() {
              that.setData({
                is_login: !that.data.is_login
              })
            }, 1000)
            wx.redirectTo({
              url: '../me',
            })
          },
          fail(resp) {
            setTimeout(function() {
              that.setData({
                is_login: !that.data.is_login
              })
            }, 1000)
            console.log(resp)
          }
        })
      },
    })

  }
})