// Simple JSON Loader - Basic direct fetching

// Load personal points
function loadPersonalPoints() {
    console.log("Loading personal points...");
    fetch('file_operations_personalpoints.json')
        .then(response => {
            console.log("Response status:", response.status);
            return response.json();
        })
        .then(data => {
            console.log("Personal points data:", data);
            displayPersonalPoints(data);
        })
        .catch(error => {
            console.error("Error loading personal points:", error);
            document.getElementById('points-list').innerHTML = '<p>Error loading data</p>';
        });
}

function displayPersonalPoints(data) {
    const container = document.getElementById('points-list');
    let html = '';
    for (const [id, points] of Object.entries(data)) {
        html += '<div class="data-item">ID: ' + id + ' | Points: ' + points + '</div>';
    }
    container.innerHTML = html || '<p>No data available</p>';
}

// Load tasks
function loadTasks() {
    console.log("Loading tasks...");
    fetch('file_operations_tasks.json')
        .then(response => {
            console.log("Response status:", response.status);
            return response.json();
        })
        .then(data => {
            console.log("Tasks data:", data);
            displayTasks(data);
        })
        .catch(error => {
            console.error("Error loading tasks:", error);
            document.getElementById('tasks-list').innerHTML = '<p>Error loading data</p>';
        });
}

function displayTasks(data) {
    const container = document.getElementById('tasks-list');
    let html = '';
    if (Array.isArray(data)) {
        for (const task of data) {
            html += '<div class="data-item">Task: ' + (task.name || 'N/A') + ' | Type: ' + (task.type || 'N/A') + '</div>';
        }
    }
    container.innerHTML = html || '<p>No data available</p>';
}

// Load trades
function loadTrades() {
    console.log("Loading trades...");
    fetch('file_operations_trades.json')
        .then(response => {
            console.log("Response status:", response.status);
            return response.json();
        })
        .then(data => {
            console.log("Trades data:", data);
            displayTrades(data);
        })
        .catch(error => {
            console.error("Error loading trades:", error);
            document.getElementById('trades-list').innerHTML = '<p>Error loading data</p>';
        });
}

function displayTrades(data) {
    const container = document.getElementById('trades-list');
    let html = '';
    if (Array.isArray(data)) {
        for (const trade of data) {
            html += '<div class="data-item">Trade: ' + (trade.name || 'N/A') + '</div>';
        }
    }
    container.innerHTML = html || '<p>No data available</p>';
}

// Auto-load on page load
window.addEventListener('load', function() {
    const path = window.location.pathname;
    if (path.includes('personalpoints.html')) {
        loadPersonalPoints();
    } else if (path.includes('tasks.html')) {
        loadTasks();
    } else if (path.includes('trades.html')) {
        loadTrades();
    }
});
