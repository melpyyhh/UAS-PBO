package controller;

import model.Registrasi;
import model.RegistrasiDAO;
import model.User;
import util.KlinikFacade;
import util.Sesi;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class SubmitCommand {
    private RegistController controller;

    public SubmitCommand(RegistController controller) {
        this.controller = controller;
    }

    public void execute(ActionEvent event) {
        String nama = controller.getNamaTextField().getText();
        String nim = controller.getNimTextField().getText();
        int umur = controller.getUmurSpinner().getValue();
        String kelas = controller.getKelasTextField().getText().toUpperCase();
        LocalDate tanggal = controller.getTanggalDatePicker().getValue();
        String jam = controller.getJamCombobox().getValue();
        String keluhan = controller.getKeluhanTextArea().getText();

        if (nama.isEmpty() || nim.isEmpty() || kelas.isEmpty() || tanggal == null || jam == null || keluhan.isEmpty()) {
            KlinikFacade.showAlert(Alert.AlertType.ERROR,"Submit Gagal", "Tolong Isi Semua Form");
            return;
        }

        if (!KlinikFacade.validateDate(tanggal)) {
            return;
        }

        Registrasi regist = new Registrasi(nama, nim, umur, kelas, tanggal, jam, keluhan);
        RegistrasiDAO.getInstance().save(regist);

        controller.clearForm();
        User currentUser = Sesi.getCurrentUser();
        KlinikFacade.sendEmail(currentUser, nama, tanggal, jam, keluhan);

        KlinikFacade.showAlert(Alert.AlertType.INFORMATION, "Registrasi Berhasil", 
            "Pembuatan Janji Temu Berhasil. Silahkan datang ke Klinik Sesuai Waktu yang di pilih");
    }
}
