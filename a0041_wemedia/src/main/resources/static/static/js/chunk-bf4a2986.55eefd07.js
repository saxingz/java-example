(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-bf4a2986"],{"4e12":function(e,a,t){"use strict";t.r(a);var l=function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"task-manage"},[t("a-card",{staticClass:"task-list-card",attrs:{bordered:!1}},[t("div",{staticClass:"table-page-search-wrapper"},[t("a-form",{attrs:{layout:"inline"}},[t("a-row",{attrs:{gutter:24}},[t("a-col",{attrs:{md:8,sm:24}},[t("a-form-item",{attrs:{label:"频道Id"}},[t("a-input",{attrs:{placeholder:"请输入任务名称"},on:{blur:e.handleSearch},model:{value:e.queryParam.channelId,callback:function(a){e.$set(e.queryParam,"channelId",a)},expression:"queryParam.channelId"}})],1)],1),t("a-col",{attrs:{md:8,sm:24}},[t("a-form-item",{attrs:{label:"频道标题"}},[t("a-input",{attrs:{placeholder:"请输入任务名称"},on:{blur:e.handleSearch},model:{value:e.queryParam.channelTitle,callback:function(a){e.$set(e.queryParam,"channelTitle",a)},expression:"queryParam.channelTitle"}})],1)],1),t("a-col",{attrs:{md:4,sm:24}},[t("span",{staticClass:"table-page-search-submitButtons"},[t("a-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("添加")])],1)])],1)],1)],1),t("a-table",{attrs:{rowKey:function(e){return e.id},dataSource:e.dataList,columns:e.taskTableTitle,pagination:e.pagination},on:{change:e.handlePageChage},scopedSlots:e._u([{key:"followTime",fn:function(a){return[t("span",[e._v(e._s(e.moment(a).format("YYYY-MM-DD HH:mm:ss")))])]}},{key:"startTime",fn:function(a){return[t("span",[e._v(e._s(e.moment(a).format("YYYY-MM-DD HH:mm:ss")))])]}},{key:"action",fn:function(a,l){return[t("a",{on:{click:function(a){return e.handleUpdate(l)}}},[e._v("修改")])]}}])})],1),t("AddChannelModal",{ref:"modal",on:{ok:e.handleSearch}})],1)},n=[],i=(t("498a"),t("99af"),t("9efd")),r="/v1/channel";function s(e,a,t){return Object(i["a"])({url:"".concat(r,"/list?page=").concat(a,"&pageNum=").concat(t),method:"post",data:e})}function o(e,a,t){return Object(i["a"])({url:"".concat(r,"/add-or-update"),method:"post",data:e})}var c=t("c1df"),d=t.n(c),u=function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("a-modal",{attrs:{title:"添加",width:590,visible:e.visible},on:{ok:e.handleOk,cancel:e.handleCancel}},[t("a-form",{attrs:{form:e.form}},[t("a-form-item",{attrs:{label:"频道ID",labelCol:e.labelCol,wrapperCol:e.wrapperCol}},[t("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["channelId",{rules:[{required:!0,whitespace:!0,message:"请输入频道ID"}]}],expression:"['channelId', {rules: [{required: true, whitespace: true, message: '请输入频道ID'}]}]"}],attrs:{placeholder:"请输入频道ID"}})],1),t("a-form-item",{attrs:{label:"频道标题",labelCol:e.labelCol,wrapperCol:e.wrapperCol}},[t("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["channelTitle",{rules:[{required:!0,whitespace:!0,message:"请输入频道标题"}]}],expression:"['channelTitle', {rules: [{required: true, whitespace: true, message: '请输入频道标题'}]}]"}],attrs:{placeholder:"请输入频道标题"}})],1),t("a-form-item",{attrs:{label:"跟进时间",labelCol:e.labelCol,wrapperCol:e.wrapperCol}},[t("a-date-picker",{directives:[{name:"decorator",rawName:"v-decorator",value:["followTime",{rules:[{type:"object",required:!0,whitespace:!0,message:"请选择跟进时间"}]}],expression:"['followTime', {rules: [{type: 'object', required: true, whitespace: true, message: '请选择跟进时间'}]}]"}],staticStyle:{width:"100%"},attrs:{showTime:""}})],1),t("a-form-item",{attrs:{label:"开始时间",labelCol:e.labelCol,wrapperCol:e.wrapperCol}},[t("a-date-picker",{directives:[{name:"decorator",rawName:"v-decorator",value:["startTime",{rules:[{type:"object",required:!0,whitespace:!0,message:"请选择开始时间"}]}],expression:"['startTime', {rules: [{type: 'object', required: true, whitespace: true, message: '请选择开始时间'}]}]"}],staticStyle:{width:"100%"},attrs:{showTime:""}})],1)],1)],1)},m=[],h={data:function(){return{form:this.$form.createForm(this),labelCol:{xs:{span:24},sm:{span:6}},wrapperCol:{xs:{span:24},sm:{span:14}},visible:!1,initialValue:{},id:""}},methods:{moment:d.a,edit:function(){this.visible=!0},handleOk:function(){var e=this,a=this.form.validateFields;a({force:!0},(function(a,t){a||(e.id&&(t.id=e.id),t.followTime=t["followTime"].valueOf(),t.startTime=t["startTime"].valueOf(),o(t).then((function(a){e.$message.success("成功修改"),e.$emit("ok")})))}))},handleCancel:function(){this.visible=!1},clearInitialValue:function(){this.id="",this.form.setFieldsValue({channelId:this.initialValue.channelId,channelTitle:this.initialValue.channelTitle,followTime:this.initialValue.followTime,startTime:this.initialValue.startTime})},editInitialValue:function(e){var a=this;this.id=e.id,this.$nextTick((function(){a.form.setFieldsValue({channelId:e.channelId,channelTitle:e.channelTitle,followTime:d()(e.followTime),startTime:d()(e.startTime)})}))}}},p=h,f=t("2877"),b=Object(f["a"])(p,u,m,!1,null,null,null),w=b.exports,T={name:"TaskManage",components:{AddChannelModal:w},data:function(){return{logParam:{jobs_type:"",jobs_name:"",status:null,custodian_id:null},dataList:[],selectedRowKeys:[],taskTableTitle:[{title:"ID",dataIndex:"id"},{title:"频道ID",dataIndex:"channelId"},{title:"频道标题",dataIndex:"channelTitle"},{title:"跟进时间",dataIndex:"followTime",scopedSlots:{customRender:"followTime"}},{title:"开始时间",dataIndex:"startTime",scopedSlots:{customRender:"startTime"}},{title:"操作",dataIndex:"actions",scopedSlots:{customRender:"action"}}],pagination:{},page:1,pageNum:20,queryParam:{channelId:"",channelTitle:""},dealQueryParam:{}}},created:function(){var e={};this.dealQueryParam=e,this.getChannelList(e,this.page,this.pageNum)},methods:{moment:d.a,getChannelList:function(e,a,t){var l=this;s(e,a,t).then((function(e){l.dataList=e.data.records;var n={current:a,pageSize:t,total:e.data.total};l.pagination=n}))},handleSearch:function(){var e={};this.queryParam.channelId.trim()&&(e.channelId=this.queryParam.channelId.trim()),this.queryParam.channelTitle.trim()&&(e.channelTitle=this.queryParam.channelTitle.trim()),this.dealQueryParam=e,this.getChannelList(e,this.page,this.pageNum)},handleAdd:function(){this.$refs.modal.clearInitialValue(),this.$refs.modal.edit()},handleUpdate:function(e){var a=this;this.$nextTick((function(){a.$refs.modal.editInitialValue(e)})),this.$refs.modal.edit()},handlePageChage:function(e){this.getChannelList(this.dealQueryParam,e.current,this.pageNum)}}},v=T,g=(t("eed8"),Object(f["a"])(v,l,n,!1,null,"4ed48608",null));a["default"]=g.exports},b543:function(e,a,t){},eed8:function(e,a,t){"use strict";var l=t("b543"),n=t.n(l);n.a}}]);