Page({
  data:{
    seller_list:[],
    arr:['0','1','2','4','5','6','7']
  },
  skip(event){
    wx.navigateTo({
      url: `../seller/seller?seller_id=${event.currentTarget.dataset.id}`,
    })
    console.log(event.currentTarget.dataset.id)
  },
  onLoad() {
    let that = this;
    wx.request({
      url: 'http://127.0.0.1:8081/seller/getsellerlist/',
      success(resp) {
        console.log(resp.data);
        that.setData({
          seller_list:resp.data
        })
      }
    })
  }
})