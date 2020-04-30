document.write("<script language='javascript' src='../static/js/main/api/baseApi.js'></script>");

//获取表数据
function getTableDate(data) {

	return postData("/getTableData", data);
}


//构造数据
function createData(data, data2) {

	return postData_json("/createData", data,data2);
}