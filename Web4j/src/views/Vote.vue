<template>
  <div class="body" style="width: 700px;height: 300px;margin-left: 390px">
    <div v-if="!isVote">
    <h1>谁是你心目中的最佳男歌手</h1>
    投他的理由：
    <el-input v-model="input" placeholder="喜欢他的理由" style="width: 300px;margin-right: 10px"></el-input>
    <el-button type="primary" @click="judge" style="margin-bottom: 10px">为他投票</el-button>
    <el-table
        :data="tableData"
        border
        stripe
        ref="tableData"
        @row-click="singleElection">
      <el-table-column prop="img" label="帅照" width="130">
        <template slot-scope="scope">
          <img :src="scope.row.img" alt="">
        </template>
      </el-table-column>
      <el-table-column prop="id" label="简介" width="400"></el-table-column>
<!--      <el-table-column prop="tip" label="现有投票数" width="100"></el-table-column>-->
      <el-table-column label="请选择">
        <template slot-scope="scope">
          <el-radio class="radio" v-model="templateSelection" :label="scope.$index">&nbsp;</el-radio>
<!--          <el-radio v-model="radio" label="1">&nbsp;</el-radio>-->
        </template>
      </el-table-column>
    </el-table>
    </div>

    <div v-if="isVote" style="margin-top: 100px">
      <h1>感谢你为【{{voteData.username}}】投上宝贵的一票</h1>
      <h2>你是第【{{voteData.vote}}】位支持他的小伙伴</h2>
      <h3 v-if="input!==''">你喜欢他的理由是：【{{input}}】</h3>
      <h3 v-else>你还没说明喜欢他的理由噢！</h3>
      <img :src="voteData.img">
      <br/>
      <el-button type="success" round @click="isVote=!isVote;input=''" style="margin-top: 10px">继续投票</el-button>
    </div>

  </div>
</template>
<script>
import {getRequest} from "@/utils/api";

export default {
  name: 'Vote',
  data() {
    return {
      isVote: false,
      voteData: {
        username: '',
        reason: '',
        img: '',
        vote: '',
      },
      input: '',
      templateSelection: '',
      // 返回数据
      tableData: [
        {
          'username': '张学友',
          'img': require('@/assets/star/Zhangxueyou.jpg'),
          'id': '张学友（Jacky Cheung），1961年7月10日出生于香港，中国香港流行乐男歌手、影视演员、作曲人，毕业于香港崇文英文书院。',
        },
        {
          'username': '周杰伦',
          'img': require('@/assets/star/JayChou.jpg'),
          'id': '周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，祖籍福建省泉州市永春县，中国台湾流行乐男歌手、音乐人、演员、导演、编剧，毕业于淡江中学。',
        },
        {
          'username': '张杰',
          'img': require('@/assets/star/Zhangjie.jpg'),
          'id': '张杰（Jason Zhang），1982年12月20日出生于四川省成都市，中国流行男歌手。2004年参加歌唱类选秀《我型我秀》，获得全国总冠军并出道。',
        },
        {
          'username': '林俊杰',
          'img': require('@/assets/star/JJ.jpg'),
          'id': '林俊杰（JJ Lin），1981年3月27日出生于新加坡，祖籍中国福建省厦门市同安区  ，华语流行乐男歌手、音乐人、潮牌主理人。',
        },
      ]
    }
  },
  methods: {
    singleElection(row) {
      this.templateSelection = this.tableData.indexOf(row);
      // alert( this.templateSelection)
      this.voteData.img = row.img;
      this.voteData.username = row.username;
    },
    judge() {
      if (this.templateSelection === '') {
        this.$message.error('请选择一个选项');
        return;
      }
      // alert( this.templateSelection)
      getRequest("/vote",this.templateSelection+1).then(res=>{
          this.voteData.vote = res.data;
          this.isVote = true;
      })
    }
  }
}
</script>

<style scoped>
.body {
  text-align: center;
}
</style>
