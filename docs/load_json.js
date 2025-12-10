// Simple JSON Loader - Basic direct fetching

// Load personal points
function loadPersonalPoints() {
    console.log("Loading personal points...");
    fetch('file_operations_residents.json')
        .then(response => {
            console.log("Response status:", response.status);
            return response.json();
        })
        .then(data => {
            console.log("Residents data:", data);
            displayPersonalPoints(data);
        })
        .catch(error => {
            console.error("Error loading personal points:", error);
            document.getElementById('points-list').innerHTML = '<p>Error loading data</p>';
        });
}

function displayPersonalPoints(data) {
    const container = document.getElementById('points-list');
    
    if (!Array.isArray(data) || data.length === 0) {
        container.innerHTML = '<div class="points-header"><div>Resident ID</div><div>Personal Points</div></div><p style="text-align:center;padding:20px;color:#666;">No data available</p>';
        return;
    }
    
    let html = '<div class="points-header"><div>Resident ID</div><div>Personal Points</div></div>';
    
    for (const resident of data) {
        html += '<div class="points-row" onclick="showBoostModal(' + 
                (resident.id || 0) + ', ' + 
                (resident.hasBoost || false) + ')">';
        html += '<div>' + (resident.id || 'N/A') + '</div>';
        html += '<div>' + (resident.personalPoints || 0) + '</div>';
        html += '</div>';
    }
    
    container.innerHTML = html;
}

function showBoostModal(residentId, hasBoost) {
    const statusText = hasBoost ? '🚀 Active Boost' : 'No Boost';
    document.getElementById('modal-boost-status').textContent = statusText;
    document.getElementById('boost-modal').style.display = 'block';
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
    
    if (!Array.isArray(data) || data.length === 0) {
        container.innerHTML = '<div class="task-header"><div>Task Name</div><div>Type</div><div>Points</div></div><p style="text-align:center;padding:20px;color:#666;">No data available</p>';
        return;
    }
    
    let html = '<div class="task-header"><div>Task Name</div><div>Type</div><div>Points</div></div>';
    
    for (const task of data) {
        html += '<div class="task-row" onclick="showTaskModal(\'' + 
                escapeHtml(task.name || 'N/A') + '\', \'' + 
                escapeHtml(task.type || 'N/A') + '\', ' + 
                (task.points || 0) + ', \'' + 
                escapeHtml(task.description || 'No description available') + '\')">';
        html += '<div>' + (task.name || 'N/A') + '</div>';
        html += '<div>' + (task.type || 'N/A') + '</div>';
        html += '<div>' + (task.points || 0) + '</div>';
        html += '</div>';
    }
    
    container.innerHTML = html;
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML.replace(/'/g, "\\'");
}

function showTaskModal(name, type, points, description) {
    document.getElementById('modal-task-name').textContent = name;
    document.getElementById('modal-task-type').textContent = type;
    document.getElementById('modal-task-points').textContent = points;
    document.getElementById('modal-task-description').textContent = description;
    document.getElementById('task-modal').style.display = 'block';
}

// Close modal when clicking X or outside
window.onclick = function(event) {
    const modal = document.getElementById('task-modal');
    if (event.target === modal || event.target.className === 'close') {
        modal.style.display = 'none';
    }
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
    
    if (!Array.isArray(data) || data.length === 0) {
        container.innerHTML = '<div class="trade-header"><div>Trade Name</div><div>Trader</div><div>Points</div></div><p style="text-align:center;padding:20px;color:#666;">No data available</p>';
        return;
    }
    
    let html = '<div class="trade-header"><div>Trade Name</div><div>Trader</div><div>Points</div></div>';
    
    for (const trade of data) {
        html += '<div class="trade-row" onclick="showTradeModal(\'' + 
                escapeHtml(trade.name || 'N/A') + '\', \'' + 
                escapeHtml(trade.traderName || 'Unknown') + '\', ' + 
                (trade.pointCost || 0) + ', \'' + 
                escapeHtml(trade.description || 'No description available') + '\')">';
        html += '<div>' + (trade.name || 'N/A') + '</div>';
        html += '<div>' + (trade.traderName || 'Unknown') + '</div>';
        html += '<div>' + (trade.pointCost || 0) + '</div>';
        html += '</div>';
    }
    
    container.innerHTML = html;
}

function showTradeModal(name, trader, points, description) {
    document.getElementById('modal-trade-name').textContent = name;
    document.getElementById('modal-trade-trader').textContent = trader;
    document.getElementById('modal-trade-points').textContent = points;
    document.getElementById('modal-trade-description').textContent = description;
    document.getElementById('trade-modal').style.display = 'block';
}

// Update window.onclick to handle all modals
window.onclick = function(event) {
    const taskModal = document.getElementById('task-modal');
    const tradeModal = document.getElementById('trade-modal');
    const boostModal = document.getElementById('boost-modal');
    
    if (taskModal && (event.target === taskModal || event.target.className === 'close')) {
        taskModal.style.display = 'none';
    }
    if (tradeModal && (event.target === tradeModal || event.target.className === 'close')) {
        tradeModal.style.display = 'none';
    }
    if (boostModal && (event.target === boostModal || event.target.className === 'close')) {
        boostModal.style.display = 'none';
    }
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
