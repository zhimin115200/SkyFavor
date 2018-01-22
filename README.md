### SkyFavor
同浏览器收藏夹，随时随地随用随变随取</br>

通过邮件验证登录</br>

接口鉴权功能，登陆后返回token，请求签名，保护接口安全</br>

使用计数功能</br>

## 接口定义：</br>

# 关于账号：</br>
登录POST /user/validateAccount </br>
发送验证邮件GET /user/sendVerify/{email}</br>
重置密码POST /user/reset</br>
注册POST /user/regist</br>


# 关于收藏夹：</br>
新建POST /folder/add</br>
删除GET /folder/delete/{folderId}</br>
修改POST /folder/modify/{folderId}</br>
查询用户所有收藏夹GET /folder/getAll/{email}</br>
查询某一个收藏夹详情GET /folder/get/{folderId}</br>
查询子文件夹GET /folder/getChildFolder/{folderId}</br>

# 关于文件：</br>
新建POST /file/add</br>
删除GET /file/delete/{fileId}</br>
修改POST /file/modify/{fileId}</br>
查询文件夹下所有文件GET /file/getAll/{folderId}</br>
查询某一个文件详情GET /file/get/{fileId}</br>
文件访问计数GET /file/visitPlus/{fileId}</br>
