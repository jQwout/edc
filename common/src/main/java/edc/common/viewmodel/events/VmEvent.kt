package edc.common.viewmodel.events

abstract class VmEvent {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

class ShowError(val throwable: Throwable) : VmEvent()
class Loading(val isLoading: Boolean) : VmEvent()