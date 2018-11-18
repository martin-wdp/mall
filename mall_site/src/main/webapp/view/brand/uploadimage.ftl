<#include "../include/common.ftl">
<@htmlHead title="图片上传"></@htmlHead>
<@htmlBody>
    <form method="post" action="${basePath}/uploadFileOneForManage.htm" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit">
    </form>
</@htmlBody>

