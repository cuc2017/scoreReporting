var Timer = function() {
	// Property: Frequency of elapse event of the timer in millisecond
	this.Interval = 1000;

	// Property: Whether the timer is enable or not
	this.Enable = new Boolean(false);

	// Event: Timer tick
	this.Tick;

	// Member variable: Hold interval id of the timer
	var timerId = 0;

	// Member variable: Hold instance of this class
	var thisObject;

	this.Start = function() {
		this.Enable = new Boolean(true);

		thisObject = this;
		if (thisObject.Enable) {
			thisObject.timerId = setInterval(function() {
				thisObject.Tick();
			}, thisObject.Interval);
		}
	};

	this.Stop = function() {
		thisObject.Enable = new Boolean(false);
		clearInterval(thisObject.timerId);
	};
};
