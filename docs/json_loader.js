// JSON Loader - Dedicated module for loading JSON files from Cloverville
// Handles file:// protocol path resolution and displays data

/**
 * Resolves the correct path for JSON files when using file:// protocol
 */
function resolveJsonPath(relativeFilename) {
    if (window.location.protocol === 'file:') {
        const currentPath = window.location.pathname;
        const docsDir = currentPath.substring(0, currentPath.lastIndexOf('/'));
        const projectRoot = docsDir.substring(0, docsDir.lastIndexOf('/'));
        return projectRoot + '/' + relativeFilename;
    }
    return '../' + relativeFilename;
}

/**
 * Generic JSON loader function
 */
function loadJsonFile(filename, onSuccess, onError) {
    const actualPath = resolveJsonPath(filename);
    console.log("Loading JSON from:", actualPath);
    
    fetch(actualPath, { cache: 'no-store' })
        .then(response => {
            if (!response.ok) {
                throw new Error('HTTP ' + response.status + ': ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log("Successfully loaded:", filename, data);
            onSuccess(data);
        })
        .catch(error => {
            console.error("Failed to load " + filename + ":", error);
            if (onError) {
                onError(error);
            }
        });
}

// --- Personal Points Loader ---
function loadPointsFromJSON() {
    loadJsonFile('docs/file_operations_personalpoints.json', 
        function(data) {
            const pointsMap = {};
            for (const [id, points] of Object.entries(data)) {
                pointsMap[String(id)] = String(points);
            }
            displayPoints(pointsMap);
        },
        function(error) {
            displayPoints({});
        }
    );
}

function displayPoints(pointsMap) {
    const container = document.getElementById("points-list");
    if (!container) return;
    
    container.innerHTML = "";
    const entries = Object.entries(pointsMap || {});
    
    if (entries.length === 0) {
        const empty = document.createElement('div');
        empty.className = 'points-empty';
        empty.textContent = 'No personal points data available.';
        container.appendChild(empty);
        return;
    }
    
    for (const [id, points] of entries) {
        const item = document.createElement("div");
        item.className = "points-item";
        item.textContent = `ID: ${id} - Points: ${points}`;
        container.appendChild(item);
    }
    
    // Update status
    const statusEl = document.getElementById('points-status');
    if (statusEl) {
        statusEl.textContent = 'Last updated: ' + new Date().toISOString();
    }
}

// --- Tasks Loader ---
function loadTasksFromJSON() {
    loadJsonFile('docs/file_operations_tasks.json',
        function(data) {
            displayTasks(data || []);
        },
        function(error) {
            displayTasks([]);
        }
    );
}

function displayTasks(tasksArray) {
    const container = document.getElementById("tasks-list");
    if (!container) return;
    
    container.innerHTML = "";
    
    if (!tasksArray || tasksArray.length === 0) {
        const empty = document.createElement('div');
        empty.className = 'tasks-empty';
        empty.textContent = 'No tasks data available.';
        container.appendChild(empty);
        return;
    }
    
    for (const task of tasksArray) {
        const item = document.createElement("div");
        item.className = "task-item";
        item.textContent = `Task: ${task.name} (Type: ${task.type})`;
        container.appendChild(item);
    }
    
    // Update status
    const statusEl = document.getElementById('tasks-status');
    if (statusEl) {
        statusEl.textContent = 'Last updated: ' + new Date().toISOString();
    }
}

// --- Trades Loader ---
function loadTradesFromJSON() {
    loadJsonFile('docs/file_operations_trades.json',
        function(data) {
            displayTrades(data || []);
        },
        function(error) {
            displayTrades([]);
        }
    );
}

function displayTrades(tradesArray) {
    const container = document.getElementById("trades-list");
    if (!container) return;
    
    container.innerHTML = "";
    
    if (!tradesArray || tradesArray.length === 0) {
        const empty = document.createElement('div');
        empty.className = 'trades-empty';
        empty.textContent = 'No trades data available.';
        container.appendChild(empty);
        return;
    }
    
    for (const trade of tradesArray) {
        const item = document.createElement("div");
        item.className = "trade-item";
        item.textContent = `Trade: ${trade.name || 'N/A'}`;
        container.appendChild(item);
    }
    
    // Update status
    const statusEl = document.getElementById('trades-status');
    if (statusEl) {
        statusEl.textContent = 'Last updated: ' + new Date().toISOString();
    }
}

// --- Auto-load on page ready ---
document.addEventListener("DOMContentLoaded", function() {
    if (window.location.pathname.includes('personalpoints.html')) {
        loadPointsFromJSON();
    } else if (window.location.pathname.includes('tasks.html')) {
        loadTasksFromJSON();
    } else if (window.location.pathname.includes('trades.html')) {
        loadTradesFromJSON();
    }
});
