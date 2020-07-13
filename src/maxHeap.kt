class MaxHeap(vararg a: Int) {

    private var array: ArrayList<Int> = ArrayList(a.size)
    private var size: Int = 0

    init {
        for(element in a) {
            insert(element)
        }
    }

    fun upHeap(index: Int) {
        val temp = array[index]
        var mutableIndex = index
        var next = (mutableIndex - 1) / 2

        while(mutableIndex > 0 && temp > array[next]) {
            array[mutableIndex] = array[next]
            mutableIndex = next
            next = (next - 1) / 2
        }

        array[mutableIndex] = temp
    }

    fun downHeap(index: Int) {
        val temp = array[index]
        var mutableIndex = index
        var next = (mutableIndex * 2) + 1

        while(mutableIndex < size / 2) {
            if(next < size - 1 && array[next] <= array[next + 1])
                next++

            if(next >= size) {
                break
            }else if(temp >= array[next]){
                break
            }

            array[mutableIndex] = array[next]
            mutableIndex = next
            next = (next * 2) + 1
        }

        array[mutableIndex] = temp
    }

    fun insert(element: Int) {
        array.add(element)
        size++
        upHeap(size - 1)
    }

    fun max(): Int {
        return array[0]
    }

    fun deleteMax(): Int {
        return if(array.isNotEmpty()) {
            val temp = array[0]
            array[0] = array[size - 1]
            array.removeAt(size - 1)
            size--
            downHeap(0)

            temp
        }else {
            -1
        }
    }

    private fun moveMax(): Int {
        return if(array.isNotEmpty()){
            val temp = array[0]
            array[0] = array[size - 1]
            array[size - 1] = temp
            size--
            downHeap(0)

            temp
        }else{
            -1
        }
    }

    private fun heapify() {
        val firstNonLeaf = (size - 1) / 2
        for(i in firstNonLeaf downTo 0) {
            downHeap(i)
        }
    }

    fun heapSort() {
        heapify()

        val heapDataSize = size

        for(i in 0 until heapDataSize) {
            moveMax()
        }

        size = heapDataSize
    }

    fun size(): Int {
        return size
    }

    fun printHeap() {
        array.forEach {
            print("$it ")
        }
        println()
    }
}

fun main() {
    val h = MaxHeap(7,212,5000,4,321,378,543,521,13,4542,234,800,560,1100)
    h.printHeap()
    h.heapSort()
    h.printHeap()
}