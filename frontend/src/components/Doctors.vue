<template>
  <div>
    <h1>Doctors</h1>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Specialization</th>
          <th>Available</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="doc in doctors" :key="doc.id">
          <td>{{ doc.name }}</td>
          <td>{{ doc.specialization }}</td>
          <td>{{ doc.available ? '✅ Yes' : '❌ No' }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "Doctors",
  data() {
    return {
      doctors: [
        { id: 1, name: "Dr. John Smith", specialization: "Cardiologist", available: true },
        { id: 2, name: "Dr. Sarah Lee", specialization: "Dermatologist", available: false },
        { id: 3, name: "Dr. Amit Patel", specialization: "Orthopedic", available: true }
      ]
    }
  }
}
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}
th, td {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}
</style>
