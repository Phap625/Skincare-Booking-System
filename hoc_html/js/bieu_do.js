// Data for the chart
const data = [120, 190, 30, 50, 200, 300];
const labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];

// Function to generate the bars
function generateChart() {
    const chartContainer = document.getElementById('chart');
    const tooltip = document.getElementById('tooltip');

    // Set the maximum value for scaling the bars
    const maxValue = Math.max(...data);

    // Generate bars and X-Axis labels
    data.forEach((value, index) => {
        const bar = document.createElement('div');
        bar.classList.add('bar');
        bar.style.height = `${(value / maxValue) * 100}%`; // Height of the bar based on the data value

        // Add the value label inside the bar (on top of the bar)
        const valueLabel = document.createElement('div');
        valueLabel.classList.add('bar-value');
        valueLabel.textContent = value;
        bar.appendChild(valueLabel);

        // Add the label at the bottom (e.g., month names)
        const label = document.createElement('div');
        label.classList.add('label');
        label.textContent = labels[index]; // Get the corresponding month name
        bar.appendChild(label);

        // Add mouse hover event to display tooltips
        bar.addEventListener('mousemove', (event) => {
            tooltip.style.left = `${event.pageX + 10}px`;
            tooltip.style.top = `${event.pageY - 30}px`;
            tooltip.style.display = 'block';
            tooltip.textContent = `${labels[index]}: ${value}`;
        });

        bar.addEventListener('mouseout', () => {
            tooltip.style.display = 'none';
        });

        // Add the bar to the chart container
        chartContainer.appendChild(bar);
    });
}

// Call the function to generate the chart
generateChart();