# 统一访问控制中心

##	API

******************************************************
       uacc
         标识：40D805FF-3776-485D-A647-3247321D73C0
         版本：1.0.0.0
         产品：cj.studio.backend.uaac.program
         公司：cj工作室
         版权：Copyright 2011
         描述：统一访问控制中心
******************************************************
http://localhost:9091/uacc/protectedObject.service
    cj.studio.backend.uacc.security.stub.IProtectedObjectStub
    用法:受保护对象

    getChildObjects
        用法:获取子对象
        返回值类型:java.util.ArrayList,说明:对象列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedObjectStub
                Rest-Command getChildObjects
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码
                arg1
                    类型:java.lang.String
                    方式：InParameter objColName
                    用法:对象集合名
                arg2
                    类型:java.lang.String
                    方式：InParameter parentObjCode
                    用法:父对象代码
                arg3
                    类型:int
                    方式：InParameter currPage
                    用法:当前页
                arg4
                    类型:int
                    方式：InParameter pageSize
                    用法:页大小

    getAllProtectedObjectCollection
        用法:获取受保护对象集合定义
        返回值类型:java.util.ArrayList,说明:对象集合列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedObjectStub
                Rest-Command getAllProtectedObjectCollection
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码

    getProtectedObjectCollection
        用法:获取受保护对象集合
        返回值类型:java.util.ArrayList,说明:对象集合
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedObjectStub
                Rest-Command getProtectedObjectCollection
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码
                arg1
                    类型:java.lang.String
                    方式：InParameter objColName
                    用法:对象集合名


http://localhost:9091/uacc/pa.service
    cj.studio.backend.uacc.security.stub.IPAStub
    用法:pa存根，用于为主体分配许可

    add
        用法:以指定的对象代码列出pa
        返回值类型:java.lang.String,说明:返回paid
        方法别名:
        命令:post
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPAStub
                Rest-Command add
        参数:
                arg0
                    类型:cj.studio.backend.uacc.security.PA
                    方式:InContent pa
                    用法:pa实体

    remove
        用法:移除pa
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPAStub
                Rest-Command remove
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter paid
                    用法:pa标识

    find
        用法:查找pa
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPAStub
                Rest-Command find
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter princode
                    用法:实体代码
                arg1
                    类型:java.lang.String
                    方式：InParameter permid
                    用法:许可标识

    listByPrincipal
        用法:以指定的主体代码列出pa
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPAStub
                Rest-Command listByPrincipal
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter princode
                    用法:主体代码

    listByObject
        用法:以指定的对象代码列出pa
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPAStub
                Rest-Command listByObject
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码


http://localhost:9091/uacc/acl.service
    cj.studio.backend.uacc.security.stub.IACLStub
    用法:访问控制列表存根

    getACE
        用法:获取指定主体和对象对应的访问控制项
        返回值类型:cj.studio.backend.uacc.security.ACE,说明:控制项，如果没有返回null
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IACLStub
                Rest-Command getACE
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter princode
                    用法:主体代码
                arg1
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码
                arg2
                    类型:java.lang.String
                    方式：InParameter permcode
                    用法:许可代码

    hasRight
        用法:判断指定主体和对象是否已分配指定的许可权限
        返回值类型:java.lang.Boolean,说明:true表示主体对对象具有该许可权限
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IACLStub
                Rest-Command hasRight
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter princode
                    用法:主体代码
                arg1
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码
                arg2
                    类型:java.lang.String
                    方式：InParameter permcode
                    用法:许可代码

    getACL
        用法:获取指定主体和对象对应的访问控制列表
        返回值类型:cj.studio.backend.uacc.security.ACL,说明:控制列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IACLStub
                Rest-Command getACL
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter princode
                    用法:主体代码
                arg1
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码

    getACLByPrincipal
        用法:获取指定主体代码对应的访问控制列表
        返回值类型:cj.studio.backend.uacc.security.ACL,说明:控制列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IACLStub
                Rest-Command getACLByPrincipal
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter princode
                    用法:主体代码

    getACLByObject
        用法:获取指定对象代码对应的访问控制列表
        返回值类型:cj.studio.backend.uacc.security.ACL,说明:控制列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IACLStub
                Rest-Command getACLByObject
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码


http://localhost:9091/uacc/principals.service
    cj.studio.backend.uacc.security.stub.IPrincipalsStub
    用法:主体。

    getChildPrincipals
        用法:获取子主体
        返回值类型:java.util.ArrayList,说明:主体列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPrincipalsStub
                Rest-Command getChildPrincipals
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码
                arg1
                    类型:java.lang.String
                    方式：InParameter principalColName
                    用法:主体集合名
                arg2
                    类型:java.lang.String
                    方式：InParameter parentPrincipalCode
                    用法:父主体代码
                arg3
                    类型:int
                    方式：InParameter currPage
                    用法:当前页
                arg4
                    类型:int
                    方式：InParameter pageSize
                    用法:页大小

    getContainsPrincipals
        用法:获取关联主体指定代码包含的相关主体列表
        返回值类型:java.util.ArrayList,说明:主体列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPrincipalsStub
                Rest-Command getContainsPrincipals
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码
                arg1
                    类型:java.lang.String
                    方式：InParameter principalColName
                    用法:主体集合名
                arg2
                    类型:java.lang.String
                    方式：InParameter relactionshipPrincipalColName
                    用法:关系主体表名
                arg3
                    类型:java.lang.String
                    方式：InParameter principalCode
                    用法:主体代码
                arg4
                    类型:int
                    方式：InParameter currPage
                    用法:当前页
                arg5
                    类型:int
                    方式：InParameter pageSize
                    用法:页大小

    getAllPrincipalCollections
        用法:获取所有主体表定义
        返回值类型:java.util.ArrayList,说明:主体表列表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPrincipalsStub
                Rest-Command getAllPrincipalCollections
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码

    getPrincipalCollection
        用法:获取指定主体表的定义
        返回值类型:java.util.ArrayList,说明:主体表
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPrincipalsStub
                Rest-Command getPrincipalCollection
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码
                arg1
                    类型:java.lang.String
                    方式：InParameter principalColName
                    用法:主体集合名


http://localhost:9091/uacc/permission.service
    cj.studio.backend.uacc.security.stub.IPermissionStub
    用法:许可存根

    remove
        用法:将分配的许可移除
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command remove
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter permid
                    用法:许可标识

    find
        用法:查找许可
        返回值类型:cj.studio.backend.uacc.security.Permission,说明:许可
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command find
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码
                arg1
                    类型:java.lang.String
                    方式：InParameter permcode
                    用法:操作代码

    permissions
        用法:获取对象的所有许可
        返回值类型:java.util.ArrayList,说明:许可集合
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command permissions
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码

    exists
        用法:是否存在许可
        返回值类型:java.lang.Boolean,说明:true是已存在
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command exists
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码
                arg1
                    类型:java.lang.String
                    方式：InParameter permcode
                    用法:操作代码

    assign
        用法:分配许可。将指定的对象关联到操作,可以为一个对象多次分配不同的操作
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command assign
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码
                arg1
                    类型:java.lang.String
                    方式：InParameter permcode
                    用法:操作代码
                arg2
                    类型:java.lang.String
                    方式：InParameter permname
                    用法:许可名

    unassign
        用法:将指定的操作从该对象关联中移除
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command unassign
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter objcode
                    用法:对象代码
                arg1
                    类型:java.lang.String
                    方式：InParameter permcode
                    用法:操作代码

    findByPermissionId
        用法:查找许可
        返回值类型:cj.studio.backend.uacc.security.Permission,说明:许可
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IPermissionStub
                Rest-Command findByPermissionId
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter permid
                    用法:许可标识


http://localhost:9091/uacc/protectedSystem.service
    cj.studio.backend.uacc.security.stub.IProtectedSystemStub
    用法:受保护系统定义

    getPage
        用法:获取一页
        返回值类型:java.util.ArrayList,说明:返回一页数据
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedSystemStub
                Rest-Command getPage
        参数:
                arg0
                    类型:int
                    方式：InParameter currPage
                    用法:系统代码
                arg1
                    类型:int
                    方式：InParameter pageSize
                    用法:系统代码

    addSystem
        用法:添加受保护系统
        方法别名:
        命令:post
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedSystemStub
                Rest-Command addSystem
        参数:
                arg0
                    类型:cj.studio.backend.uacc.security.ProtectedSystemInfo
                    方式:InContent info
                    用法:受保护系统信息

    updateSystem
        用法:添加受保护系统
        方法别名:
        命令:post
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedSystemStub
                Rest-Command updateSystem
        参数:
                arg0
                    类型:cj.studio.backend.uacc.security.ProtectedSystemInfo
                    方式:InContent info
                    用法:受保护系统信息

    removeSystem
        用法:移除受保护系统
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedSystemStub
                Rest-Command removeSystem
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码

    getSystem
        用法:获取一个系统信息
        返回值类型:cj.studio.backend.uacc.security.ProtectedSystemInfo,说明:如果不存在则返回空
        方法别名:
        命令:get
        协议:http/1.1
        Rest Header:
                Rest-StubFace cj.studio.backend.uacc.security.stub.IProtectedSystemStub
                Rest-Command getSystem
        参数:
                arg0
                    类型:java.lang.String
                    方式：InParameter sysCode
                    用法:系统代码


