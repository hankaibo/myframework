/**
 * @param isAuto Boolean 是否自动上传
 * @param fileSize Number,String 上传文件的大小
 * @param fileDesc String 上传文件的描述
 * @param fileType String 上传文件的格式
 * @param filePath String 上传文件的路径
 * @param isMulti Boolean 是否允许一次上传多个
 * @param sumUpload Number 文件上传量限制
 */
function myUploadify(isAuto, fileSize, fileDesc,fileType,filePath,isMulti,sumUpload) {
    $('#file_upload').uploadify({
        /*必须*/
        'swf'       : contentPath+'/resources/js/uploadify/uploadify.swf', // swf的路径，该属性必须填写
        'uploader'  : contentPath+'/upload;jsessionid='+sessionId, // 服务端脚本文件路径 ， 该属性必须填写
        
        /*Options - 属性*/
        'auto'              : isAuto, // 当文件被添加到队列中是否文件自动上传，默认为true即自动上传，可以设置auto为false阻止自动上传。
        'buttonClass'       : '', // 添加到uploadify按钮的类名，默认为空。
        'buttonCursor'      : 'hand', // 鼠当鼠标移动到浏览按钮上时，光变的样式，这个属性有两个可选值：'hand（默认）'，'arrow（箭头）'
//        'buttonImage'       : null, // 设置“文件选择按钮”的背景图片地址，3.2版本的默认按钮在uploadify.css样式中，引用时需要修改路劲或者对buttonImage属性指定路劲。
        'buttonText'        : '浏览文件', // 设置“文件选择按钮”的文本文字，默认为“SELECT FILES”
        'checkExisting'     : false, // 检查现在上传的文件是否已存在于目标文件夹（以名称辨别），存在返回1，不存在返回0
        'debug'             : false, // 是否设置为调试模式，默认为false
        'fileObjName'       : 'myFile', // 服务器端接受文件对象的名称，默认为Filedate，所以java服务器端接受文件对象的名称为File FileDate;
        'fileSizeLimit'     : fileSize, // 单文件上传的最大文件大小，可以使用数字或字符串，字符串是包含单位（B, KB, MB,GB）的字符串， 使用数字， 则默认为KB,0为无限制
        'fileTypeDesc'      : fileDesc, // 选择的文件的描述。这个字符串出现在浏览文件对话框中文件类型下拉框处。默认：All Files
        'fileTypeExts'      : fileType, // 允许上传的文件类型，默认：'*.*'，可以设置多种类型('*.doc; *.docx')，官方文档说可以使用手动键入文件名（包含后缀）的方式来绕过这个设置，建议在服务器断对文件类型再次进行判断。
        'formData'          : {'session' : sessionId}, //(在这里利用它来确定上传文件的位置)设置提交到服务器端的动态数据，服务器端可以获取这些提交的值，同获取form表单提交的值一样。直接在此处添加数据，只能添加静态数据， 如果想添加动态数据，需要使用onUploadStart事件.传输数据JSON格式
//        'formData'          : {'choosePath' : filePath}, //(在这里利用它来确定上传文件的位置)设置提交到服务器端的动态数据，服务器端可以获取这些提交的值，同获取form表单提交的值一样。直接在此处添加数据，只能添加静态数据， 如果想添加动态数据，需要使用onUploadStart事件.传输数据JSON格式
        'height'            : 30, // 设置浏览按钮的高度，单位像素（px）
        'itemTemplate'      : false, // 自定义一个模板添加到队列的每个项目中，有4个可用的标签：instanceId（uploadify的实例id），fileId(标示文件的唯一Id)，fileName(添加到队列中文件的名称)，fileSize（文件大小）；使用方法：${fileID}
        'method'            : 'post',// 提交文件的方式是以post还是get
        'multi'             : isMulti,// 是否允许一次选择多个文件，设置为false，则每次只能选择一个文件，但是可以通多次选择来选择多个
        'preventCaching'    : true, // 设置为true，防止swf文件缓存，防止缓存的方式是在swf的URL后面添加一个随机参数 
        'progressData'      : 'percentage', //在上传文件时，是显示上传速度，还是显示已上传的百分比，该属性有两个可选值：'percentage(默认)', 'speed'
        'queueID'           : 'queue', // 用指定DOM元素显示文件上传队列，该属性为指定DOM的ID 
        'queueSizeLimit'    : 10, // 同一时间上传队列允许的最大文件数，当选定添加到队列的文件数超过指定大小，将会触发onSelectError事件 
        'removeCompleted'   : true, // 默认在文件上传完成后，将其从队列中移除，设置为false后， 将在队列中保留上传完成的文件
        'removeTimeout'     : 1, // 当removeCompleted设置为true时，该属性设置 在文件上传完毕后，经过多少秒后从队列中移除
        'requeueErrors'     : false, // 设置为true时，将对上传产生错误的文件重新添加到队列中，并在此尝试上传
        'successTimeout'    : 60, // 上传完成后，等待服务器响应的时间，默认30秒，当超过这个时间，那么将认为该文件上传成功
        'uploadLimit'       : sumUpload, //文件上传量限制。这个与queueSizeLimit不同，后者是队列中同一时间最多允许有多少个文件，前者是文件总上传量。当超过限定值是会触发onUploadError事件
        'width'             : 80, //   浏览按钮的宽度，单位像素（px）
        
        /*Events 事件*/
//        'overrideEvents' : [ 'onUploadProgress' ], //设置Uploadify里的哪些事件可以被用户重写。'overrideEvents' :{'onCancel', 'onInit'}表示'onCancel'事件和'onInit'事件可以被用户重写
        // 取消队列中上传的文件时触发，该事件获取一个参数file，该参数包含被取消的文件的信息
//        'onCancel' : function(file) {
//            alert('文件' + file.name + '已经被取消上传了！');
//        },
        // 当执行cancel方法，且方法参数为'*'时触发该事件， 该事件获取一个参数queueItemCount，该参数为被取消的文件的个数
//        'onClearQueue' : function(queueItemCount) {
//             alert(queueItemCount + '文件已经从上传队列中移除。');
//        },
        // 当使用destroy方法时触发
//        'onDestroy' : function() {
//             alert('我被销毁了');
//        },
        // 关闭文件浏览窗口时触发，该事件获取一个参数queueData，该参数有五个属性：filesSelected:浏览窗口中选中的文件数；filesQueued:添加到队列中的文件数；filesReplaced:被替换的文件个数；filesCancelled:被取消上传到队列的文件数；filesErrored:上传到队列时出错的文件数。该事件可以被重写
//        'onDialogClose' :function(swfuploadifyQueue) {
//            if( swfuploadifyQueue.filesErrored > 0 ){
//                alert('添加至队列时有' +swfuploadifyQueue.filesErrored +'个文件发生错误n'
//                    +'错误信息:'+swfuploadifyQueue.errorMsg
//                    +'n选定的文件数:'+swfuploadifyQueue.filesSelected
//                    +'n成功添加至队列的文件数:'+swfuploadifyQueue.filesQueued
//                    +'n队列中的总文件数量:'+swfuploadifyQueue.queueLength);
//            }
//        },
        // 打开文件浏览窗口时触发，该事件里的代码可能会在文件浏览窗口关闭时才执行
//        'onDialogOpen' :function(){
//            alert('打开窗口了！');
//        },
        // 禁用Uploadify时触发，通过disable方法来禁用Uploadify
//        'onDisable' :function(){
//        },
        // 启用Uploadify时触发，通过disable方法来启用Uploadify
//        'onEnable' :function(){
//        },
        // 在初始化时，若检测不到浏览器有兼容性的flash版本时触发
//        'onFallback' :function(){
//            alert("OK!");
//        },
        // 在Uploadify初始化时触发，该事件获取一个参数instance，该参数为Uploadify的一个实例
//        'onInit' : function(instance) {
//            // alert('The queue ID is ' + instance.settings.queueID);
//        },
        // 队列中的文件上传完毕后触发，该事件获取一个参数queueData，该参数有两个属性：uploadsSuccessful:成功上传的文件数量；uploadsErrored:上传失败的文件数量
//        'onQueueComplete' :function(queueData){
//            alert( '成功上传的文件数: ' + queueData.uploadsSuccessful);
//        },
        // 文件在文件浏览窗口被选择并被添加到文件队列时触发，该事件获取一个参数file，该参数为被添加的文件的一个实例，该事件可以被重写
//        'onSelect' :function(file){
//            alert( 'id: ' + file.id
//                    + ' - 索引: ' + file.index
//                    + ' - 文件名: ' + file.name
//                    + ' - 文件大小: ' + file.size
//                    + ' - 类型: ' + file.type
//                    + ' - 创建日期: ' + file.creationdate
//                    + ' - 修改日期: ' + file.modificationdate
//                    + ' - 文件状态: ' + file.filestatus);
//        },
        // 当被选择文件在添加到文件队列出错时触发，该事件获取三个参数file、errorCode、errorMsg，file：发生错误的文件对象实例；errorCode：错误码，用以确定错误的类型，内容可能包含三个常量(QUEUE_LIMIT_EXCEEDED:选择的文件数量超过限定值；FILE_EXCEEDS_SIZE_LIMIT:文件大小超过限定值；ZERO_BYTE_FILE:文件没有大小；INVALID_FILETYPE:文件类型不是规定的类型)
        'onSelectError' :function(file,errorCode,errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");
                    break;
                case -110:
                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
                    alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
            alert('对不起，文件'+ file.name + '选择错误！，错误原因：' +QUEUE_LIMIT_EXCEEDED);
        },
        // 在flash文件加载成功并准备好后触发
//        'onSWFReady' :function(){
//        },
        // 队列中每个文件上传完毕后触发，该事件获取一个参数file，该参数为上传完毕的文件的实例，如果想知道上传完毕的文件具体是上传成功还是上传失败，建议使用onUploadSuccess和onUploadError事件。事件可重写
//        'onUploadComplete' : function(file) {
//             alert('The file ' + file.name + ' finished processing.');
//        },
        // 上传某个文件出错时触发，该事件获取三个参数：file:出错文件对象的实例；errorCode:错误码，是否与onSelectError的errorCode相同，未知；errorMsg:错误信息
        'onUploadError' : function(file, errorCode, errorMsg) {
             alert('The file ' + file.name + ' could not be uploaded: ' + errorMsg);
             alert("文件:" + file.name + " 上传失败!错误原因："+ errorMsg);
        },
        // 每更新一个文件的上传进度的时候触发，该事件获取三个参数：file:更新进度的文件实例；
//        'onUploadProgress' : function(file, fileBytesLoaded, fileTotalBytes) {
//            $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
//        },
        // 每个文件开始上传时触发，该事件获取一个参数file：开始上传的文件实例 
        'onUploadStart' : function(file) {
             $("#file_upload").uploadify("settings", "someOtherKey", 2);
        },
        // 每个文件上传成功时触发，该事件获取三个参数：file:上传成功的文件对象实例；data:服务器返回的数据；response:来自服务器的响应，true表示成功，false表示服务器无响应，当超过successTimeout设定的时间，则默认返回true
        'onUploadSuccess' : function(file, data, response) {
//             alert(file.name + ' | ' + response + ':' + data);
//             var jsonarray= $.parseJSON(data);
//             alert(jsonarray.imageName);
//             $.post(contentPath+'material/manage/ajaxEcho.action?imageName='+jsonarray.imageName,
//                 function(data){
//                     var ddd=$('#userGroups').append( $('.ajaxEcho').clone(true)).attr("style","block");
//                     $("").prepend("<tr class=\"file_item\"><td class=\"table_cell file_content\"><strong class=\"file_name\">"+data.title+"</strong> <span class=\"frm_input_box appended\"> <input class=\"frm_input\" type=\"text\" data-id=\""+data.id+"\"/> <a class=\"js_rename frm_input_append icon16_common enter_gray\" href=\"javascript:void(0);\" data-id=\"1\">确定</a> </span> <div id=\"fileWrp1\" class=\"file_wrp\" data-id=\"1\"> <a class=\"media_img\" href=\"\" target=\"_blank\"> <img class=\"wxmImg Zoomin\" width=\"100\" height=\"55\" src=\"${contentPath}upload/image/${WEIXIN}/"+data.saveName+"\"> </a> </div> </td> <td class=\"table_cell file_info\"> <em class=\"file_size\">"+data.size+"KB</em> </td> <td class=\"table_cell file_opr\"> <a class=\"js_edit\" href=\"javascript:void(0);\" data-id=\"1\" data-type=\"1\"> <i class=\"icon18_common edit_gray\">编辑</i> </a> <a class=\"js_del\" href=\"\" data-id=\"1\" data-type=\"1\"> <i class=\"icon18_common del_gray\">删除</i> </a> </td></tr>");
//             },'json');
            
            
            window.location.href=contentPath+"material/manage/list.action";
        }
    });
};
