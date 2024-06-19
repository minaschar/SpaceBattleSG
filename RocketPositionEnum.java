public enum RocketPositionEnum  
{
    LEFT("left"),
    RIGHT("right");
    
    private final String position;

    private RocketPositionEnum(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
