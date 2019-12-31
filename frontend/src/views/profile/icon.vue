<template>
  <div class="components-container">
    <pan-thumb :image="image"/>
    <el-button type="primary" icon="el-icon-upload" style="position: absolute;bottom: 15px;margin-left: 40px;"
               @click="toggleShow">
      修改头像
    </el-button>
    <image-cropper
      field="multipartFile"
      @crop-success="cropSuccess"
      @crop-upload-success="cropUploadSuccess"
      @crop-upload-fail="cropUploadFail"
      v-model="show"
      :width="300"
      :height="300"
      :url="url"
      :params="params"
      :headers="headers"
      img-format="png"
    />
  </div>
</template>
<script>
  import PanThumb from '@/components/PanThumb'
  import ImageCropper from 'vue-image-crop-upload';
  import {getToken} from "../../utils/auth";
  import {modifyIcon} from "../../api/profile";

  export default {
    name: "icon",
    components: {PanThumb, ImageCropper},
    data() {
      return {
        url: process.env.VUE_APP_BASE_API + "/upload",
        image: this.$store.getters.avatar,
        show: false,
        params: {
          access_token: getToken(),
        },
        headers: {
          smail: '*_~'
        },
      }
    },
    methods: {
      //展开选择文件界面
      toggleShow() {
        this.show = !this.show;
      },

      cropSuccess(image, field) {
        console.log('-------- crop success --------');
        this.image = image;
      },

      /**
       * 上传成功更新头像
       * @param jsonData
       * @param field
       */
      cropUploadSuccess(jsonData, field) {
        console.log('-------- upload success --------');
        console.log(jsonData);
        console.log('field: ' + field);

        // 更新头像
        modifyIcon({
          username: this.$store.getters.name,
          path: jsonData.data.path
        }).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          })

          // 更新 vuex 中的头像
          this.$store.dispatch('user/setAvatar', jsonData.data.path)
        }).catch(() => {
        })
      },

      cropUploadFail(status, field) {
        console.log('-------- upload fail --------');
        console.log(status);
        console.log('field: ' + field);
      }
    }
  }
</script>

<style scoped>

</style>
