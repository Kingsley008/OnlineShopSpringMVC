<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
        <#if user>
            <#if user.usertype==1>卖家<#else>买家</#if>你好，<span class="name">${user.username}</span>！<a href="/javawebshop/shop/logout">[退出]</a>
        <#else>
            请<a href="/javawebshop/shop/login">[登录]</a>
        </#if>
        </div>
        <ul class="nav">
            <li><a href="/javawebshop/shop/bookonline">首页</a></li>
            <#if user && user.usertype==0>
            <li><a href="/javawebshop/shop/account">账务</a></li>
            <li><a href="/javawebshop/shop/settleAccount">购物车</a></li>
            </#if>
            <#if user && user.usertype==1>
            <li><a href="/public">发布</a></li>
            </#if>
        </ul>
    </div>
</div>


