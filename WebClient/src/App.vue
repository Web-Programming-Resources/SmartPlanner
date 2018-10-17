<template>
  <div>
    <b-navbar sticky toggleable="md" type="dark" variant="info">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand>Smart Planner</b-navbar-brand>
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown v-bind:text="lang" right>
          <b-dropdown-item v-on:click="lang='pl'">pl</b-dropdown-item>
          <b-dropdown-item v-on:click="lang='en'">en</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
    <b-navbar fixed="bottom" toggleable="md" type="dark" variant="info">
        <b-navbar-brand class="footer">Copyright&copy; 2018 by GKN. All rights reserved.</b-navbar-brand>
    </b-navbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      lang: String
    }
  },
  created() {
    this.lang = "pl";
  }
}
</script>

<style scoped>
  .footer {
    font-style: italic;
    opacity: 0.25;
  }
</style>
