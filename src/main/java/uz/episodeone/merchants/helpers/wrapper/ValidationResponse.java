package uz.episodeone.merchants.helpers.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResponse<G,F> {

    public ValidationResponse(List<G> global, List<F> fields) {
        this.global = global;
        this.fields = fields;
    }

    private List<G> global;

    private List<F> fields;
}
