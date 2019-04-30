### 整合apache的BeanUtils和Convert
   
### 说明
   - 这里的apache-confing2依赖于BeanUtils-1.9。  BeanUtils2目前在Maven中还没有,还不稳定
   - apache-Convert模块在maven中没有，就先使用BeanUtils中的Convert。   
     但是为了之后的升级后所有的转化都使用，就需要做个适配   @see com.wlh.convert.IConverter
