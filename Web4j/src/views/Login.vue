<template>
  <div class="about">
    <h1>System Login Window</h1>

    <div class="stand">
      <div class="outer-screen">
        <div class="inner-screen">
          <div class="form">
            <div class="el-form">
              <el-form
                  :model="ruleForm"
                  status-icon
                  :rules="rules"
                  ref="ruleForm"
                  label-width="100px"
                  class="demo-ruleForm"
              >
                <el-form-item label="用户名" prop="username">
                  <el-input
                      v-model.trim="ruleForm.username"
                      placeholder="请输入用户名"
                  ></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="password">
                  <el-input
                      type="password"
                      v-model="ruleForm.password"
                      autocomplete="off"
                      placeholder="请输入密码"
                  ></el-input>
                </el-form-item>
                <el-form-item label="验证码" prop="code">
                  <el-input
                      v-model.trim="ruleForm.code"
                      style="width: 140px"
                      placeholder="请输入验证码"
                  > </el-input>
                    <img :src="captchaUrl" @click="updateCaptcha" style="float: right"/>
                </el-form-item>
                <el-checkbox v-model="checked" style="margin-left: 77px"
                >记住密码
                </el-checkbox
                >
              </el-form>
            </div>
            <button class="submit" @click="submitForm('ruleForm')">登录</button>
            <a  @click="forget">忘记密码?</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {postRequest} from "@/utils/api";

export default {
  name: 'Login',
  data() {
    var checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'))
      } else {
        callback()
      }
    }

    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('密码不能为空'))
      } else {
        callback()
      }
    }

    var checkCode = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('验证码不能为空'))
      } else {
        callback()
      }
    }

    return {
      // captchaUrl: '/captcha?time=' + new Date(),
      checked: true,
      ruleForm: {
        username: '',
        password: '',
        code: ''
      },
      rules: {
        username: [{validator: checkName, trigger: 'blur'}],

        password: [{validator: validatePass, trigger: 'blur'}],

        code: [{validator: checkCode, trigger: 'blur'}]
      }
    }
  },
  methods: {
    forget(){
      this.$router.push("/about")
    },
    updateCaptcha() {
      // this.captchaUrl = '/captcha?time=' + new Date();
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          postRequest('/login', this.ruleForm).then(res => {
            alert(JSON.stringify(res));
          });
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.el-form {
  padding-right: 25px;
  margin-bottom: 25px;
}

h1 {
  color: #1abc9d;
  text-align: center;
  font-family: Arial;
  font-weight: normal;
  margin: 2em auto 0px;
}

.outer-screen {
  background: #13202c;
  width: 900px;
  height: 540px;
  margin: 50px auto;
  border-radius: 20px;
  -moz-border-radius: 20px;
  -webkit-border-radius: 20px;
  position: relative;
  padding-top: 35px;
}

.outer-screen:before {
  content: '';
  background: #3e4a53;
  border-radius: 50px;
  position: absolute;
  bottom: 20px;
  left: 0px;
  right: 0px;
  margin: auto;
  z-index: 9999;
  width: 50px;
  height: 50px;
}

.outer-screen:after {
  content: '';
  background: #ecf0f1;
  width: 900px;
  height: 88px;
  position: absolute;
  bottom: 0px;
  border-radius: 0px 0px 20px 20px;
  -moz-border-radius: 0px 0px 20px 20px;
  -webkit-border-radius: 0px 0px 20px 20px;
}

.stand {
  position: relative;
}

.stand:before {
  content: '';
  position: absolute;
  bottom: -150px;
  border-bottom: 150px solid #bdc3c7;
  border-left: 30px solid transparent;
  border-right: 30px solid transparent;
  width: 200px;
  left: 0px;
  right: 0px;
  margin: auto;
}

.stand:after {
  content: '';
  position: absolute;
  width: 260px;
  left: 0px;
  right: 0px;
  margin: auto;
  border-bottom: 30px solid #bdc3c7;
  border-left: 30px solid transparent;
  border-right: 30px solid transparent;
  bottom: -180px;
  box-shadow: 0px 4px 0px #7e7e7e;
}

.inner-screen {
  width: 800px;
  height: 340px;
  background: #1abc9d;
  margin: 0px auto;
  padding-top: 80px;
}

.form {
  width: 400px;
  height: 330px;
  background: #edeff1;
  margin: -40px auto;
  padding-top: 20px;
  border-radius: 10px;
  -moz-border-radius: 10px;
  -webkit-border-radius: 10px;
}

.submit {
  display: block;
  background: #1abc9d;
  width: 314px;
  padding: 12px;
  cursor: pointer;
  color: #fff;
  border: 0px;
  margin: auto;
  border-radius: 5px;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  font-size: 17px;
  transition: all 0.3s ease;
  height: 50px;
}

.submit:hover {
  background: #09cca6;
}

a {
  text-align: center;
  font-family: Arial;
  color: gray;
  display: block;
  margin: 15px auto;
  text-decoration: none;
  transition: all 0.3s ease;
  font-size: 12px;
}

a:hover {
  color: #1abc9d;
}

::-webkit-input-placeholder {
  color: gray;
}

:-moz-placeholder {
  /* Firefox 18- */
  color: gray;
}

::-moz-placeholder {
  /*
Firefox 19+ */
  color: gray;
}

:-ms-input-placeholder {
  color: gray;
}
</style>
