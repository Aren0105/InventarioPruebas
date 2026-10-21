const ctx = document.getElementById("revenueChart");
new Chart(ctx, {
  type: "line",
  data: {
    labels: ["Jan","Feb","Mar","Apr","May","Jun"],
    datasets: [{
      data: [0, 150, 300, 600, 1200, 2400],
      borderColor: "#21b36b",
      backgroundColor: "rgba(33,179,107,0.12)",
      fill: true,
      tension: 0.35
    }]
  },
  options: { plugins: { legend: { display: false } }, scales: { y: { beginAtZero: false } } }
});