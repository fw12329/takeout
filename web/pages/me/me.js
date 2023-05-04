Page({
  data: {
    token: '',
    username: '',
    is_login: false,
    abc: '屈伸',
    specs:[{specs_name:"底料",spec_list:[{}]}],
    orders:[{'product':1,'quantity':10,'spec':[{spec:7,specs:8},{}]}]

  },
  test1() {
    wx.request({
      url: 'http://127.0.0.1:8081/user/order/getorderdetails/',
      method:"post",
      data:{
        order_id:25
      },
      success(resp) {
        console.log(resp.data)
      }

    })
  },
  
  test() {
    let that = this
    wx.request({
      url: 'http://127.0.0.1:8081/test/',
      method:'get',
      success(resp) {
        console.log(resp.data)
        // let jsonObj = JSON.parse(resp.data)
      }
      
    })
    
  }
})