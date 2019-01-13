<template>
  <div>
    <b-navbar sticky toggleable="md" type="dark" variant="info">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand :to="{path: '/'}">Smart Planner</b-navbar-brand>
      <b-navbar-nav class="ml-auto">
        <b-nav-item v-if="isAuthenticated" active-class="active font-weight-bold" :to="{path:'/'}">{{navbar.headers.displayPlans}}</b-nav-item>
        <b-nav-item v-if="isAuthenticated" active-class="active font-weight-bold" :to="{path:'/CreatePlan'}">{{navbar.headers.createPlan}}</b-nav-item>
        <b-nav-item v-if="isAuthenticated" active-class="active font-weight-bold" @click="logout()">{{navbar.headers.logout}}</b-nav-item>
        <b-nav-item-dropdown v-bind:text="$store.getters.language" right>
          <b-dropdown-item v-on:click="chooseLanguage('pl')">pl</b-dropdown-item>
          <b-dropdown-item v-on:click="chooseLanguage('en')">en</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
     <router-view></router-view>
  </div>
</template>

<script>
export default {
  data() {
    return {
      navbar:
      {
        headers:
        {
          logout: "",
          displayPlans: "",
          createPlan: "",
        }
      }
    }
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    }
  },
  created() {
    this.chooseLanguage('pl');
  },
  methods: {
    chooseLanguage(lang) {
      this.$store.commit('setLanguage', lang);
      switch(lang) {
        case "pl":
          this.navbar.headers.logout = "Wyloguj";
          this.navbar.headers.displayPlans = "Wyświetl plany";
          this.navbar.headers.createPlan = "Stwórz Plan";
        break;
        case "en":
          this.navbar.headers.logout = "Logout";
          this.navbar.headers.displayPlans = "Show plans";
          this.navbar.headers.createPlan = "Create Plan";
        break;
      }
    },
    logout() {
      this.$store.commit("logout");
      this.$root.$router.push({path: "/Login"});
    }
  }
}
</script>

<style lang="css">
.btn {
  border-radius: 0 !important;
  }
.table-caption {
  caption-side: top;
  text-align: center;
  padding: 0 0;
  color: #fff;
  background-color: #17a2b8;
}
</style>
