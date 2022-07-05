<template>
  <div style="text-align: center">
    <h2>快和你的小伙伴打个招呼吧！</h2>
    <p>
      <el-input v-model="input" placeholder="给他打个招呼吧" style="width: 300px;margin-right: 10px"></el-input>
      <el-button type="primary" round @click="sayHi">打招呼</el-button>
    </p>
    <h2>{{ hi }}</h2>
  </div>
</template>

<script>
import {getRequestWithParams} from "@/utils/api";

export default {
  name: "Hello",
  data() {
    return {
      input: '',
      hi: ''
    }
  },
  methods: {
    sayHi() {
      getRequestWithParams('/hello',this.input).then(res => {
        this.hi = res.message+'，'+res.data;
      })
    }
  },
}
</script>

<style scoped>

</style>
