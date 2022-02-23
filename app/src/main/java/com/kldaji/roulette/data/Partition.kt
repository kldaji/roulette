package com.kldaji.roulette.data

data class Partition(val text: String, val color: Int, val ratio: Float) {
    var startAngle = 0f
        private set
    var endAngle = 0f
        private set

    fun setAngle(_startAngle: Float, _endAngle: Float) {
        startAngle = _startAngle
        endAngle = _endAngle
    }
}
