package dtos;

public class RoomDTO {

	private String id, name, building;
	private int floor;

	public RoomDTO(String id, String name, String building, int floor) {
		this.id = id;
		this.name = name;
		this.building = building;
		this.floor = floor;
	}

	public RoomDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "RoomDTO{" + "id=" + id + ", name=" + name + ", building=" + building + ", floor=" + floor + '}';
	}

}
