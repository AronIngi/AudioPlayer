package hi.verkefni.vidmot;

public enum View {
    MAIN("heima-view.fxml"),
    LISTI("listi-view.fxml");

    private String fileName;

    View(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
