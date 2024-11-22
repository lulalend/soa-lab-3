
type pagesForView = {
    pagesStart: Page[];
    pagesMiddle?: Page[];
    pagesEnd?: Page[];
}

type Page = {
    item: number;
    active: boolean;
}

export function getPagesForView(totalPages: number, currentPage: number): pagesForView {
    const totalPagesArray = new Array(totalPages).fill(undefined);

    if (totalPages <= 10) {
        return {
            pagesStart: getShortTotalPagesArray(totalPagesArray, currentPage)
        }
    }
    else {
        const isNumberInMiddle = currentPage > 4 && currentPage < (totalPages - 3);
        return {
            pagesStart: getPagesStart(totalPages, currentPage),
            pagesMiddle: isNumberInMiddle ? getPagesMiddle(currentPage) : undefined,
            pagesEnd: getPagesEnd(totalPages, currentPage)
        }
    }
}

function getShortTotalPagesArray(totalPages: number[], currentPage: number): Page[] {
    return totalPages.map((page, index): Page => {
        if (index + 1 === currentPage) {
            return {
                item: currentPage,
                active: true
            }
        }
        return {
            item: index + 1,
            active: false
        }
    })
}

function getPagesStart(totalPages: number, currentPage: number): Page[] {
    switch (currentPage) {
        case 1:
            return [
                {
                    item: 1,
                    active: true
                },
                {
                    item: 2,
                    active: false
                },
            ]
        case 2:
            return [
                {
                    item: 1,
                    active: false
                },
                {
                    item: 2,
                    active: true
                },
                {
                    item: 3,
                    active: false
                },
            ]
        case 3:
            return [
                {
                    item: 1,
                    active: false
                },
                {
                    item: 2,
                    active: false
                },
                {
                    item: 3,
                    active: true
                },
                {
                    item: 4,
                    active: false
                }
            ]
        case 4:
            const result = [
                {
                    item: 1,
                    active: false
                },
                {
                    item: 2,
                    active: false
                },
                {
                    item: 3,
                    active: false
                },
                {
                    item: 4,
                    active: true
                }
            ];

            if (totalPages >= 5) {
                result.push({
                    item: 5,
                    active: false
                })
            }

            if (totalPages === 6) {
                result.push({
                    item: 6,
                    active: false
                })
            }

            return result;
        default:
            return [
                {
                    item: 1,
                    active: false
                },
                {
                    item: 2,
                    active: false
                }
            ]

    }
}

function getPagesEnd(totalPages: number, currentPage: number) {
    switch (currentPage) {
        case totalPages:
            return [
                {
                    item: totalPages - 1,
                    active: false
                },
                {
                    item: totalPages,
                    active: true
                },
            ]
        case totalPages - 1:
            return [
                {
                    item: totalPages - 2,
                    active: false
                },
                {
                    item: totalPages - 1,
                    active: true
                },
                {
                    item: totalPages,
                    active: false
                },
            ]
        case totalPages - 2:
            return [
                {
                    item: totalPages - 3,
                    active: false
                },
                {
                    item: totalPages - 2,
                    active: true
                },
                {
                    item: totalPages - 1,
                    active: false
                },
                {
                    item: totalPages,
                    active: false
                }
            ]
        case totalPages - 3:
            return [
                {
                    item: totalPages - 4,
                    active: false
                },
                {
                    item: totalPages - 3,
                    active: true
                },
                {
                    item: totalPages - 2,
                    active: false
                },
                {
                    item: totalPages - 1,
                    active: false
                },
                {
                    item: totalPages,
                    active: false
                }
            ]
        default:
            return [
                {
                    item: totalPages - 1,
                    active: false
                },
                {
                    item: totalPages,
                    active: false
                }
            ]

    }
}

function getPagesMiddle(currentPage: number) {
    return [
        {
            item: currentPage - 1,
            active: false,
        },
        {
            item: currentPage,
            active: true
        },
        {
            item: currentPage + 1,
            active: false,
        },
    ]
}