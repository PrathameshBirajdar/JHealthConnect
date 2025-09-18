<template>
  <div id="layout">
    <nav class="sidebar">
      <ul>
        <li><a href="#" @click="currentPage = 'Home'">Home</a></li>
        <li><a href="#" @click="currentPage = 'Doctors'">Doctors</a></li>
        <li><a href="#" @click="currentPage = 'Appointments'">Appointments</a></li>
        <li><a href="#" @click="currentPage = 'Insurance'">Insurance</a></li>
      </ul>
    </nav>
    <main class="content">
      <component :is="currentPage" />
    </main>
  </div>
</template>

<script>
import Home from './components/Home.vue'
import Doctors from './components/Doctors.vue'
import Appointments from './components/Appointments.vue'
import Insurance from './components/Insurance.vue'

export default {
  name: 'App',
  components: { Home, Doctors, Appointments, Insurance },
  data() {
    return {
      currentPage: 'Home'
    }
  }
}
</script>

<style>
#layout {
  display: flex;
  height: 100vh;
}
.sidebar {
  width: 200px;
  background: #2c3e50;
  color: white;
  padding: 20px;
}
.sidebar ul {
  list-style: none;
  padding: 0;
}
.sidebar li {
  margin: 15px 0;
}
.sidebar a {
  color: white;
  text-decoration: none;
}
.content {
  flex: 1;
  padding: 20px;
}
</style>
