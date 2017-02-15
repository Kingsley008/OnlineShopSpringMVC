<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if !type  || type != 1>class="z-sel"</#if> ><a href="/javawebshop/shop/bookonline">所有内容</a></li>
                <#if user && user.usertype == 0><li <#if type == 1>class="z-sel"</#if> ><a href="/javawebshop/shop/bookonline?type=1">未购买的内容</a></li></#if>
            </ul>
        </div>
    </div>


    <#if !productList || !productList?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
        <#if user && user.usertype == 0 && type == 1>
            <#list productList as x>
                <#if !isBuy>
                <li id="p-${x.id}">
                    <a href="/javawebshop/shop/show?id=${x.id}" class="link">
                        <div class="img"><img src="${x.image}" alt="${x.title}"></div>
                        <h3>${x.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span></div>
                    </a>
                </li>
                </#if>
            </#list>
        <#else>
            <#list productList as x>
                <li id="p-${x.id}">
                    <a href="/javawebshop/shop/show?id=${x.id}" class="link">
                        <div class="img"><img src="${x.image}" alt="${x.title}"></div>
                        <h3>${x.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span></div>
                        <#if user && user.usertype == 0 && isBuy><span class="had"><b>已购买</b></span></#if>
                        <#if user && user.usertype == 1 && isBuy><span class="had"><b>已售出</b></span></#if>
                    </a>
                    <#if user && user.usertype==1 && !isSell><span class="u-btn u-btn-normal u-btn-xs del" data-del="${x.id}">删除</span></#if>
                </li>
            </#list>
        </#if>
        </ul>
    </div>
    </#if>
</div>
<#include "/include/footer.ftl">
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/pageIndex.js"></script>
</body>
</html>