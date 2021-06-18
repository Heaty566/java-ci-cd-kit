
package dtos;

public class ComputerDTO {

	private String id, cpu, hardDisk, ram, vga, monitor;
	private RoomDTO room;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpu() {
		return cpu;
	}

	@Override
	public String toString() {
		return "ComputerDTO{" + "id=" + id + ", cpu=" + cpu + ", hardDisk=" + hardDisk + ", ram=" + ram + ", vga=" + vga
				+ ", monitor=" + monitor + ", room=" + room + '}';
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getVga() {
		return vga;
	}

	public void setVga(String vga) {
		this.vga = vga;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}

	public ComputerDTO() {
	}

	public ComputerDTO(String id, String cpu, String hardDisk, String ram, String vga, String monitor, RoomDTO room) {
		this.id = id;
		this.cpu = cpu;
		this.hardDisk = hardDisk;
		this.ram = ram;
		this.vga = vga;
		this.monitor = monitor;
		this.room = room;
	}

}
