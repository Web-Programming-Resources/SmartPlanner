<template>
  <b-row class="text-left align-items-center">
    <b-col cols="3" class="mt-4 ml-auto mr-auto">
      <b-alert :show="showAlert" variant="danger">{{this.error}}</b-alert>
      <b-card>
        <b-form @submit="onSubmit">
          <b-form-group id="exampleInputGroup1" label="Username" label-for="username">
            <b-form-input id="username" type="text" v-model="form.username" required placeholder="Enter username">
            </b-form-input>
          </b-form-group>
          <b-form-group id="password" label="Password" label-for="password">
            <b-form-input id="password" type="password" v-model="form.password" required placeholder="Enter password">
            </b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Login</b-button>
        </b-form>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      error: "",
      showAlert: false
    }
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      var data = {
          accessToken: "1",
          username: this.form.username,
      }
      this.$store.commit('login', data);
      this.$router.push({path: '/'});
    }
  }
}
</script>