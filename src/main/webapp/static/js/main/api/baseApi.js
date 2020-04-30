document.write("<script language='javascript' src='../static/js/element/axios.min.js'></script>");
document.write("<script language='javascript' src='../static/js/element/qs.min.js'></script>");
  // get请求
  function getData(url, data) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'get',
        url: url,
        params: data
      }).then(response => {
        resolve(response.data)
      })
        .catch(ex => {
          reject(ex)
        })
    })
  };

  // post请求
  function postData(url, data) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'POST',
        url: url,
        data: Qs.stringify(data),
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(response => {
        resolve(response.data)
      })
        .catch(ex => {
          reject(ex)
        })
    })
  }


    function postData_json(url, data,data2) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'POST',
        url: url,
        data: Qs.stringify({"tableNames":data,"userName":data2.userName,"packageName":data2.packageName,"savePath":data2.savePath}),
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(response => {
        resolve(response.data)
      })
        .catch(ex => {
          reject(ex)
        })
    })
  }
