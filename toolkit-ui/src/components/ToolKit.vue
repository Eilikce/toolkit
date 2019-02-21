<template>
  <div class="toolKit">
    <h1>Eilikce工具箱</h1>
    <div class="toolKit-left">
      <h2>Elasticsearch工具</h2>
      <ul>
        <li>
            <h3>准备数据</h3>
            <span>从远程地址获取数据：</span><input v-model="sourceUrl" placeholder="ES地址">
            <button v-on:click="paiRequest">执行</button>
            <br/>
            <span>从本地文件获取数据：</span><input v-model="bakDataFile" placeholder="文件名">
            <button v-on:click="paiLocalRequest">执行</button>
            <br/>
            <button v-on:click="showBakDataFile">显示备份文件</button>
        </li>
        <li>
            <h3>推送数据</h3>
            <span>目标ES地址：</span><input v-model="destUrl" placeholder="ES地址">
            <button v-on:click="caiRequest">执行</button>
        </li>
      </ul>
      <h2>测试工具</h2>
      <ul>
        <li>
          <span>目标地址：</span><input v-model="testUrl" placeholder="ES地址">
          <button v-on:click="testGetRequest">GET</button>
          <button v-on:click="testPostRequest">POST</button>
        </li>
        <li>
          <textarea v-model="testRequestBody" placeholder="例：{name:'eilikce',age:27}"></textarea>
        </li>
      </ul>
    </div>
    <div class="toolKit-right">
      <h2>响应结果</h2>
      <ul>
        <li>
          <pre>{{ msg }}</pre>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

import axios from 'axios'

export default {
  name: 'HelloWorld',
  data () {
    return {
      sourceUrl: '192.168.95.120:9200',
      bakDataFile: '',
      paiResponse: '',
      destUrl: '192.168.95.120:9200',
      testUrl: 'http://127.0.0.1:8080/mysql/query',
      testRequestBody: '',
      msg: ''
    }
  },
  methods: {
    baseRequest: function (url, requestBody, method) {
      let request
      switch (method) {
        case 'GET':
          request = axios.get(url, {timeout: 3000})
          break
        default:
          request = axios.post(url, requestBody, {timeout: 3000})
          break
      }
      request.then((response) => {
        this.refreshMsg(response)
      })
        .catch((error) => {
          this.refreshError(error)
        })
    },
    refreshMsg: function (response) {
      this.msg = response.data
    },
    refreshError: function (response) {
      this.msg = response
    },
    testGetRequest: function () {
      axios.get(this.testUrl, {timeout: 3000})
        .then((response) => {
          this.msg = response
        })
        .catch((error) => {
          this.msg = error
        })
    },
    testPostRequest: function () {
      axios.post(this.testUrl, this.testRequestBody, {timeout: 3000})
        .then((response) => {
          this.msg = response
        })
        .catch((error) => {
          this.msg = error
        })
    },
    paiRequest: function () {
      if (this.sourceUrl === '') {
        this.msg = '请输入ES地址'
        return
      }
      this.baseRequest('/elasticsearch/pai', {
        url: this.sourceUrl,
        bakDataFile: this.bakDataFile
      })
    },
    paiLocalRequest: function () {
      if (this.bakDataFile === '') {
        this.msg = '请输入数据备份文件名'
        return
      }
      this.paiRequest()
    },
    caiRequest: function () {
      this.baseRequest('/elasticsearch/cai', {
        url: this.destUrl
      })
    },
    showBakDataFile: function () {
      this.baseRequest('/elasticsearch/list', {},
        'GET')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.toolKit {
  text-align: left;
}
.toolKit-left {
  float: left;
  width: 50%;
  text-align: left;
}
.toolKit-right {
  float: left;
  width: 50%;
  text-align: left;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  width: 100%;
  margin: 2px 10px;
}
a {
  color: #42b983;
}
textarea {
  width: 350px;
  height: 100px;
}
</style>
