"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[650],{650:function(e,r,s){s.r(r),s.d(r,{default:function(){return d}});var a=function(){var e=this,r=e._self._c;return r("div",[r("el-card",{staticStyle:{width:"60%"}},[r("el-form",{ref:"formRef",staticStyle:{"padding-right":"50px","background-color":"#0777c7"},attrs:{model:e.user,rules:e.rules,"label-width":"100px"}},[r("el-form-item",{attrs:{label:"Current Password",prop:"password"}},[r("el-input",{attrs:{"show-password":"",placeholder:"Enter current password"},model:{value:e.user.password,callback:function(r){e.$set(e.user,"password",r)},expression:"user.password"}})],1),r("el-form-item",{attrs:{label:"New Password",prop:"newPassword"}},[r("el-input",{attrs:{"show-password":"",placeholder:"Enter new password"},model:{value:e.user.newPassword,callback:function(r){e.$set(e.user,"newPassword",r)},expression:"user.newPassword"}})],1),r("el-form-item",{attrs:{label:"Confirm New Password",prop:"confirmPassword"}},[r("el-input",{attrs:{"show-password":"",placeholder:"Confirm new password"},model:{value:e.user.confirmPassword,callback:function(r){e.$set(e.user,"confirmPassword",r)},expression:"user.confirmPassword"}})],1),r("div",{staticStyle:{"text-align":"center","margin-bottom":"20px"}},[r("el-button",{attrs:{type:"primary"},on:{click:e.update}},[e._v("Confirm Changes")])],1)],1)],1)],1)},o=[],t=(s(4114),{name:"Password",data(){const e=(e,r,s)=>{""===r?s(new Error("Please confirm your password")):r!==this.user.newPassword?s(new Error("Passwords do not match")):s()};return{user:JSON.parse(localStorage.getItem("xm-user")||"{}"),rules:{password:[{required:!0,message:"Please enter your current password",trigger:"blur"}],newPassword:[{required:!0,message:"Please enter your new password",trigger:"blur"}],confirmPassword:[{validator:e,required:!0,trigger:"blur"}]}}},created(){},methods:{update(){this.$refs.formRef.validate((e=>{e&&this.$request.put("/updatePassword",this.user).then((e=>{"200"===e.code?(localStorage.removeItem("xm-user"),this.$message.success("Password changed successfully"),this.$router.push("/login")):this.$message.error(e.msg)}))}))}}}),l=t,n=s(1656),u=(0,n.A)(l,a,o,!1,null,"8aab6f9a",null),d=u.exports}}]);
//# sourceMappingURL=650.3dbea2c5.js.map