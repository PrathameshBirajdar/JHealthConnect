<template>
  <div id="app">
    <!-- Top Search Bar -->
    <header class="topbar">
      <h1 class="logo">JHealthConnect</h1>
      <div class="search-container">
        <input type="text" placeholder="Search doctors, appointments, insurance..." v-model="searchQuery" />
        <button @click="search">Search</button>
      </div>
    </header>

    <div class="layout">
      <!-- Sidebar Navigation -->
      <aside class="sidebar">
        <router-link to="/">🏠 Home</router-link>
        <router-link to="/doctors">👨‍⚕️ Doctors</router-link>
        <router-link to="/appointments">📅 Appointments</router-link>
        <router-link to="/insurance">💳 Insurance</router-link>
      </aside>

      <!-- Page Content -->
      <main class="content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      searchQuery: ""
    };
  },
  methods: {
    search() {
      alert("Searching for: " + this.searchQuery);
      // later we can connect this with backend search API
    }
  }
};
</script>

<style>
/* Top Search Bar */
.topbar {
  background: #2c3e50;
  color: white;
  padding: 10px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  margin: 0;
  font-size: 1.4rem;
}

.search-container {
  display: flex;
  align-items: center;
  gap: 8px;
}

.search-container input {
  padding: 6px 10px;
  border: none;
  border-radius: 4px;
  width: 250px;
}

.search-container button {
  background: #42b983;
  color: white;
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
}

/* Layout */
.layout {
  display: flex;
}

/* Sidebar */
.sidebar {
  background: #34495e;
  color: white;
  width: 200px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  padding: 15px;
}

.sidebar a {
  color: white;
  text-decoration: none;
  margin: 10px 0;
  font-weight: bold;
}

.sidebar a.router-link-exact-active {
  background: #42b983;
  padding: 8px;
  border-radius: 4px;
}

/* Content */
.content {
  flex: 1;
  padding: 20px;
}
</style>
