package part2.labwork4.fourth;

public class DictionaryController {
    private DictionaryView view;
    private DictionaryModel model;

    public DictionaryController(DictionaryView view, DictionaryModel model) {
        this.view = view;
        this.model = model;
    }

    public void handleFindButtonAction() {
        String english = view.getEnglishInputFieldText();
        if (english.isEmpty()) {
            view.showAlert("Input Error",
                    "Please enter an English word to find.");
            return;
        }
        String ukrainian = model.findWord(english);
        if (ukrainian == null || ukrainian.isEmpty()) {
            view.setResultAreaText("Word not found");
        } else {
            view.setResultAreaText(ukrainian);
        }
    }

    public void handleAddButtonAction() {
        String english = view.getEnglishInputFieldText();
        String ukrainian = view.getUkrainianInputFieldText();
        if (english.isEmpty() || ukrainian.isEmpty()) {
            view.showAlert("Input Error",
                    "Please enter both an English and a Ukrainian word to add.");
            return;
        }
        model.addWord(english, ukrainian);
        view.setResultAreaText("Word added successfully");
    }

    public void handleShowAllButtonAction() {
        String allWords = model.getAllWords();
        view.setResultAreaText(allWords);
    }
}
